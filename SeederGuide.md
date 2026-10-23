# Seeder Guide

How database seeding works in this project, and how to control which seeders run.

Framework code: `shared-service/src/main/java/com/codymitra/shared_service/seeders/`

| File | Role |
|------|------|
| `Seeder.java` | Interface every seeder implements: `name()`, `order()`, `runOnStartup()`, `seed()` |
| `DatabaseSeeder.java` | Runs at app startup and decides which seeders to run |
| `SeedDataReader.java` | Reads a JSON array from `modules/<feature>/data/*.json` into a list |

---

## Current seeders

| Name | Class | Order | Runs on startup | Data |
|------|-------|-------|-----------------|------|
| `user_type` | `modules/user_type/seeders/UserTypeSeeder` | 5 | ✅ yes | ADMIN, EMPLOYEE, VENDOR, SUPPLIER |
| `account_nature` | `modules/account_nature/seeders/AccountNatureSeeder` | 10 | ❌ no | Assets, Liabilities, Equity, Revenues, Expenses |
| `demo_user` | `modules/user/seeders/DemoUserSeeder` | 50 | ❌ no | 3 demo users + linked employees |

All seeders are **idempotent**: they skip rows that already exist, so running them again is always safe.

---

## 1. Control with command-line flags (one run)

| Start the app with | What runs |
|---|---|
| *(nothing)* | Only seeders with `runOnStartup() = true` (currently `user_type`) |
| `--seed` | **Every** seeder (includes `demo_user`!) |
| `--seed=demo_user` | Only `demo_user` |
| `--seed=user_type,account_nature` | Only the listed seeders |

**Terminal** (from `codymitra-service/`):

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--seed=demo_user
```

**IntelliJ:** Run → Edit Configurations → `CodymitraServiceApplication` → **Program arguments** → `--seed=demo_user`.
Remove the argument afterwards so it doesn't run every time.

An unknown name only logs a warning: `No seeder named 'xyz'. Available: [...]`.

---

## 2. Control with `application.properties` (every startup)

File: `codymitra-service/src/main/resources/application.properties`

```properties
# run EVERY seeder on every startup (default: false)
app.seed.enabled=true
```

| `app.seed.enabled` | No `--seed` flag | With `--seed...` flag |
|---|---|---|
| `false` (default) | `runOnStartup` seeders only | The flag decides |
| `true` | All seeders | The flag decides |

---

## 3. Control inside the seeder class

```java
@Override
public boolean runOnStartup() {
    return true;     // runs automatically on every startup
    // return false; // runs only with --seed or --seed=<name> (default)
}

@Override
public int order() {
    return 5;        // lower runs first; default 100
}
```

Use `runOnStartup() = true` only for **master data the app can't work without**
(e.g. `user_type`: registration needs the ADMIN row). Never use it for demo data.

Use `order()` when one seeder needs another's data. For example, `demo_user` (50) needs `user_type` (5).

---

## Common tasks

| I want to… | Do this |
|---|---|
| Load demo users once | Start with `--seed=demo_user`, then start normally |
| Re-seed after truncating a table | Start with `--seed=<name>` |
| Seed everything on a fresh DB | Start with `--seed` |
| Stop a seeder running automatically | Make its `runOnStartup()` return `false` |
| Disable a seeder completely | Remove `@Component` from its class (Spring won't register it) |
| Run seeders from code (test, admin API) | Inject `DatabaseSeeder` → `seedAll()` or `seed(List.of("demo_user"))` |

---

## Adding a new seeder

1. Put the data in `shared-service/src/main/java/com/codymitra/shared_service/modules/<feature>/data/<feature>.json`.
   (The shared-service `pom.xml` copies `**/data/*.json` onto the classpath.)
2. Create `modules/<feature>/seeders/<Feature>Seeder.java`:

```java
@Component
@RequiredArgsConstructor
public class BankTypeSeeder implements Seeder {

    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/bank_type/data/bank_type.json";

    private final BankTypeRepository bankTypeRepository;
    private final SeedDataReader seedDataReader;

    @Override
    public String name() {
        return "bank_type";
    }

    @Override
    public int order() {
        return 20;
    }

    @Override
    public void seed() {
        seedDataReader.readList(DATA_FILE, BankTypeEntity.class).stream()
                .filter(bankType -> !bankTypeRepository.existsByName(bankType.getName()))
                .forEach(bankTypeRepository::save);
    }
}
```

3. Always filter out rows that already exist (`existsBy...`) so the seeder stays idempotent.
4. Each seeder runs in its own transaction. If it throws, only that seeder's rows are rolled back.

---

## Proposed: off switch and exclude list (not implemented yet)

**The gap:** there's currently no way to stop `runOnStartup` seeders without editing code, and a bare
`--seed` also loads demo data. Two new properties fix this:

```properties
# turn off automatic (runOnStartup) seeders, e.g. in production or tests (default: true)
app.seed.on-startup=false

# never run these seeders, even with --seed or app.seed.enabled=true
app.seed.exclude=demo_user
```

### Code change: `shared-service/.../seeders/DatabaseSeeder.java`

**Add fields** below `seedOnStartup`:

```java
    @Value("${app.seed.on-startup:true}")
    private boolean runStartupSeeders;

    @Value("${app.seed.exclude:}")
    private Set<String> excluded;
```

**Replace the last `else` in `run(...)`:**

```java
        } else if (runStartupSeeders) {
            runSeeders(seeders.stream().filter(Seeder::runOnStartup).toList());
        }
```

**Replace `runSeeders(...)`** so excluded seeders are always skipped:

```java
    private void runSeeders(List<Seeder> toRun) {
        List<Seeder> allowed = toRun.stream()
                .filter(seeder -> !excluded.contains(seeder.name()))
                .sorted(Comparator.comparingInt(Seeder::order))
                .toList();

        allowed.forEach(seeder -> {
            log.info("Seeding: {}", seeder.name());
            transactionTemplate.executeWithoutResult(status -> seeder.seed());
        });
        log.info("Seeding finished ({} seeder(s))", allowed.size());
    }
```

**Update the class Javadoc:**

```java
 *   app.seed.on-startup=false       don't run runOnStartup seeders automatically
 *   app.seed.exclude=a,b            never run these seeders
```

### Recommended settings per environment

| Environment | Properties |
|---|---|
| Local dev | *(defaults)*; use `--seed=demo_user` when needed |
| Test | `app.seed.on-startup=false` (tests seed what they need) |
| Production | `app.seed.exclude=demo_user` (master data still seeds; demo data never can) |
