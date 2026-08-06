# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

`accountErpApi` (Maven artifact `codymitra-accountErpApi`, group `com.codymitra`) is a Spring Boot microservices backend for an accounting/ERP product ("Codymitra"). It is split into five service modules, all on Java 25:

- **codymitra-service** — main application service. Runs on port `8060` with context path `/api`, backed by PostgreSQL via Spring Data JPA. This is the only module with real datasource config and the only one currently runnable end-to-end.
- **auth-service** — authentication service. Skeleton only: `configs/` and `controllers/` packages exist but are empty (no source files yet).
- **shared-service** — common library of entities/controllers reused across services, organized by feature under `modules/<feature>/entities`. Currently: `entities/BaseEntity` (audit columns: `created_by`, `last_modified_by`, `created_at`, `last_modified_at`), `modules/account_ledger/entities/AccountLedgerEntity`, `modules/stock_item/entities/StockItemEntity`. Also has a stub `controllers/HealthController` (not yet a real Spring `@RestController`).
- **journal-service** — standalone module, currently just the generated Spring Boot skeleton (no custom code yet).
- **voucher-service** — standalone module, currently just the generated Spring Boot skeleton (no custom code yet).

### Reactor structure (important)

The root `pom.xml` is a parent/reactor POM (`packaging=pom`, Spring Boot parent `3.5.16`) but its `<modules>` list only includes **codymitra-service, auth-service, shared-service**. These three share the root parent and its dependency set (spring-boot-starter-web, spring-boot-starter-data-jpa, postgresql driver, commons-lang3, lombok).

**journal-service and voucher-service are NOT part of the root reactor.** They each declare their own `spring-boot-starter-parent` (version `4.1.0` — a different major version than the root's `3.5.16`) and must be built/run independently from their own directory, not via the root POM.

### Known inconsistency to be aware of

`codymitra-service/pom.xml` configures `<mainClass>com.codymitra.CodymitraServiceApplication</mainClass>` in the spring-boot-maven-plugin, but the actual application class lives at `com.codymitra.codymitra_service.CodymitraServiceApplication` (package name was auto-corrected by Spring Initializr because `codymitra-service` with a hyphen is not a valid Java package segment — see `codymitra-service/HELP.md`). Fix the pom's `mainClass` if packaging/repackaging breaks.

## Common commands

There is no root Maven wrapper; each module ships its own `mvnw`/`mvnw.cmd`. Run module commands from inside that module's directory, or use system `mvn` from the root for the reactor.

Reactor build (codymitra-service, auth-service, shared-service together), from repo root:
```
mvn clean install
```

Single reactor module, e.g. codymitra-service, from repo root:
```
mvn -pl codymitra-service -am clean install
```

Run the main app (from `codymitra-service/`):
```
./mvnw spring-boot:run
```

Standalone modules (journal-service, voucher-service) must be built/run from within their own directory:
```
cd journal-service && ./mvnw spring-boot:run
cd voucher-service && ./mvnw spring-boot:run
```

Tests, all modules in the reactor:
```
mvn test
```

Tests for one module (from that module's directory):
```
./mvnw test
```

Single test class:
```
./mvnw test -Dtest=CodymitraServiceApplicationTests
```

## Datasource

`codymitra-service` connects to a local PostgreSQL instance (see `codymitra-service/src/main/resources/application.properties`):
- URL: `jdbc:postgresql://localhost:5432/account_erp`
- `spring.jpa.hibernate.ddl-auto=update` — schema is auto-updated from entities on startup, no migration tool in use.

The database must exist locally (`account_erp`) before starting codymitra-service.

## Architecture conventions

- Entities live under `modules/<feature_name>/entities/<Feature>Entity.java`, package-by-feature rather than package-by-layer. Follow this layout when adding new domain entities to `shared-service`.
- Shared/base persistence concerns (audit fields) belong in `shared-service`'s top-level `entities.BaseEntity`, extended via `@EqualsAndHashCode(callSuper = true)` by feature entities (see `StockItemEntity`).
- Lombok (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) is the standard for entity boilerplate across modules.
- `codymitra-service`'s pom explicitly wires the Lombok annotation processor into both `default-compile` and `default-testCompile` executions of `maven-compiler-plugin`, and excludes Lombok from the repackaged fat jar — replicate this pattern if adding annotation processors to other modules in the reactor.
