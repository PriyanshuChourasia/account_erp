# Register user: ADMIN, no userable

**Rule:** when someone registers through `POST /api/auth/register`:

- `user_type` is set to **ADMIN** automatically. The client can't choose it.
- `userable` stays **NULL**. No employee is created or linked at registration.
- The user is saved and gets an access token and a refresh token, as before.

```
users
┌───────┬───────────┬───────────────┬─────────────┐
│ name  │ user_type │ userable_type │ userable_id │
├───────┼───────────┼───────────────┼─────────────┤
│ Priya │ ADMIN     │ NULL          │ NULL        │  ← registered
└───────┴───────────┴───────────────┴─────────────┘
```

> This **replaces** the "create an employee at registration" plan in `UserTypeAdminChanges.md` (Step 6 there).

Paths are relative to `shared-service/src/main/java/com/codymitra/shared_service/` unless they start with `auth-service/`.

---

## Already done (no action needed)

| What | File |
|------|------|
| `UserTypeCodeEnum` (ADMIN, EMPLOYEE, VENDOR, SUPPLIER) | `modules/user_type/enums/UserTypeCodeEnum.java` |
| `UserTypeRepository.findByCode` | `modules/user_type/repositories/UserTypeRepository.java` |
| User type seed data + seeder (runs on every startup) | `modules/user_type/data/user_type.json`, `modules/user_type/seeders/UserTypeSeeder.java` |
| `UserTypeLookup.get(UserTypeCodeEnum)` | `modules/user_type/services/UserTypeLookup.java` |
| `userType` (`@ManyToOne`, NOT NULL) on `UserEntity` | `modules/user/entities/UserEntity.java` |

---

## Change 1: Make userable nullable in `UserEntity`

**File:** `modules/user/entities/UserEntity.java`

**Before:**

```java
    @Column(name = "userable_type",nullable = false)
    @JoinColumn(name = "userable_id",nullable = false)
    private Userable userable;
```

**After:**

```java
    @Column(name = "userable_type")
    @JoinColumn(name = "userable_id")
    private Userable userable;
```

(`nullable` defaults to `true`, so removing it is enough.)

Leave the read-only `userableId` / `userableType` fields and `userType` as they are.

---

## Change 2: Drop NOT NULL in the database (one time)

`spring.jpa.hibernate.ddl-auto=update` **adds** columns but **never changes** an existing column's
nullability. If the app has already started once with `nullable = false`, the columns are still
NOT NULL in Postgres, and registration fails with `null value in column "userable_type" violates not-null constraint`.

Run once against `account_erp`:

```sql
alter table users alter column userable_type drop not null;
alter table users alter column userable_id   drop not null;
```

Check:

```sql
select column_name, is_nullable
from information_schema.columns
where table_name = 'users'
  and column_name in ('userable_type', 'userable_id', 'user_type_id');
```

Expected: `userable_type = YES`, `userable_id = YES`, `user_type_id = NO`.

> If `users` already has rows **without** a `user_type_id`, startup fails when Hibernate adds that
> NOT NULL column. In dev, clear the table first: `truncate table users cascade;`

---

## Change 3: Set ADMIN in `registerUser` (auth-service)

**File:** `auth-service/src/main/java/com/codymitra/auth_service/services/impl/AuthServiceImpl.java`

### 3a. Imports: add

```java
import com.codymitra.shared_service.modules.user_type.enums.UserTypeCodeEnum;
import com.codymitra.shared_service.modules.user_type.services.UserTypeLookup;
import org.springframework.transaction.annotation.Transactional;
```

### 3b. Fields: add `UserTypeLookup`

```java
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserTypeLookup userTypeLookup;
```

### 3c. Replace `registerUser(...)` with:

```java
    @Override
    @Transactional
    public AuthResponseDTO registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.email())) {
            throw new DataAlreadyExistsException("User already exists with this email");
        }
        if (userRepository.existsByContactNo(userRegisterDTO.contactNo())) {
            throw new DataAlreadyExistsException("User already exists with this contact number");
        }

        UserEntity user = new UserEntity();
        user.setName(userRegisterDTO.name());
        user.setEmail(userRegisterDTO.email());
        user.setContactNo(userRegisterDTO.contactNo());
        user.setCountryCode(userRegisterDTO.countryCode());
        user.setAltContactNo(userRegisterDTO.altContactNo());
        user.setActive(true);

        String username = generateUsername(userRegisterDTO.name());
        user.setUsername(username);

        String code = generateCode();
        user.setCode(code);

        user.setPassword(passwordEncoder.encode(userRegisterDTO.password()));

        // every registered user is an ADMIN; no userable (employee etc.) at registration
        user.setUserType(userTypeLookup.get(UserTypeCodeEnum.ADMIN));

        userRepository.save(user);

        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
        return new AuthResponseDTO(accessToken, refreshToken);
    }
```

What changed compared with the current method:
- **Added `@Transactional`:** loading the ADMIN user type and saving the user happen in one transaction.
- **Added `user.setUserType(userTypeLookup.get(UserTypeCodeEnum.ADMIN));`:** this sets the role.
- **Removed `log.info("register service now");`:** a debug line. Keep it if you want.
- **No `setUserable(...)`:** it stays `null`.

No change to auth-service's `UserRegisterDTO`. Don't add a `userType` field to it, or clients could register themselves as anything.

If `UserTypeLookup` throws `User type ADMIN is not seeded`, the `user_types` table is empty.
`UserTypeSeeder` runs on every startup, so restart codymitra-service, or run `--seed=user_type`.

---

## Change 4: Delete the unused `register()` in shared-service

Nothing calls it (checked with grep). Its old code also builds a user without a `userType`, which
now fails on insert because `user_type_id` is NOT NULL.

1. **File:** `modules/user/services/UserService.java`. Delete:

   ```java
       UserDTO register(UserRegisterDTO userRegisterDTO);
   ```

   and its `import ...dtos.UserRegisterDTO;`.

2. **File:** `modules/user/services/impl/UserServiceImpl.java`. Delete the whole
   `public UserDTO register(UserRegisterDTO userRegisterDTO) { ... }` method and its
   `import ...dtos.UserRegisterDTO;`.

3. **Delete file:** `modules/user/dtos/UserRegisterDTO.java` (shared-service's copy only.
   **Keep** `auth-service/.../dtos/UserRegisterDTO.java`).

---

## Change 5 (optional): Show role in the profile

**File:** `auth-service/src/main/java/com/codymitra/auth_service/dtos/ProfileResponseDTO.java`. Add the last field:

```java
        @NotBlank(message = "Contact No cannot be blank")
        Long contactNo,

        String userType
```

**File:** `auth-service/src/main/java/com/codymitra/auth_service/mapper/AuthMapper.java`

```java
        return new ProfileResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getContactNo(),
                user.getUserType() != null ? user.getUserType().getCode() : null
        );
```

**File:** `auth-service/.../services/impl/AuthServiceImpl.java`. Annotate `profile(...)`:

```java
    @Override
    @Transactional(readOnly = true)
    public ProfileResponseDTO profile(String token) {
```

`userType` is LAZY and `spring.jpa.open-in-view=false`, so without the transaction
`getUserType().getCode()` throws `LazyInitializationException`.

---

## Change 6 (later): Link a userable after registration

Registration leaves `userable` empty. When the admin later gets a linked record (for example an
employee profile), set it like this:

```java
UserEntity user = userRepository.findById(userId).orElseThrow(...);
user.setUserable(userableResolver.resolve(UserableTypeEnum.EMPLOYEE, employeeId));
userRepository.save(user);
```

Code that reads `user.getUserable()` must now handle `null`.

---

## Checklist

1. [ ] `UserEntity`: remove `nullable = false` from userable `@Column` / `@JoinColumn`
2. [ ] DB: `alter table users alter column userable_type drop not null;` (and `userable_id`)
3. [ ] `AuthServiceImpl`: inject `UserTypeLookup`, add `@Transactional`, set ADMIN
4. [ ] Delete `register()` from `UserService` / `UserServiceImpl`, and shared `UserRegisterDTO`
5. [ ] Optional: `userType` in `ProfileResponseDTO` + `@Transactional(readOnly = true)` on `profile()`
6. [ ] Test (below)

## Test

1. Start codymitra-service. The log should show `Seeding: user_type`.
2. Register:

   ```
   POST http://localhost:8060/api/auth/register
   ```

   ```json
   {
     "name": "Priya Sharma",
     "email": "priya@example.com",
     "password": "secret123",
     "countryCode": "+91",
     "contactNo": 9876543210
   }
   ```

   Expect `accessToken` and `refreshToken` in the response.

3. Check the DB:

   ```sql
   select u.name, u.username, ut.code as user_type, u.userable_type, u.userable_id
   from users u
   join user_types ut on ut.id = u.user_type_id
   where u.email = 'priya@example.com';
   ```

   Expected: `user_type = ADMIN`, `userable_type = NULL`, `userable_id = NULL`.

4. Register again with the same email → `User already exists with this email`.
5. Log in with the generated `username` and the password → tokens returned.
