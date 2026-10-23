# Register flow: Employee + ADMIN user

**Rule:** when someone registers:

1. An **Employee** record is created from the registration data.
2. A **User** is created and linked to that employee: `userable_type = EMPLOYEE`, `userable_id = <employee id>`.
3. The user's role (`user_type`) is set to **ADMIN**.

Paths are relative to `shared-service/src/main/java/com/codymitra/shared_service/` unless stated otherwise.
Background on the morph mapping: [MorphRelation.md](MorphRelation.md) / [UserMorphSteps.md](UserMorphSteps.md).

```
POST /api/auth/register
        │
        ▼
 employees ──────────────┐            users
 ┌──────────┬──────────┐ │  ┌───────┬───────────┬───────────────┬─────────────┐
 │ id       │ code     │ │  │ name  │ user_type │ userable_type │ userable_id │
 ├──────────┼──────────┤ │  ├───────┼───────────┼───────────────┼─────────────┤
 │ 7f3c...  │ EMP_4821 │ └─►│ Priya │ ADMIN     │ EMPLOYEE      │ 7f3c...     │
 └──────────┴──────────┘    └───────┴───────────┴───────────────┴─────────────┘
```

**Already done:** `Userable` interface, `EmployeeEntity implements Userable`, `@Any` mapping in `UserEntity`,
`UserableTypeEnum` (value `"EMPLOYEE"`), `UserableResolver`.

---

## Step 1: User type codes

**New file:** `modules/user_type/enums/UserTypeCodeEnum.java`

```java
package com.codymitra.shared_service.modules.user_type.enums;

public enum UserTypeCodeEnum {
    ADMIN,
    EMPLOYEE
}
```

The enum `name()` is what gets stored in `user_types.code`. Never rename a value once rows exist.

---

## Step 2: Find a user type by code

**File:** `modules/user_type/repositories/UserTypeRepository.java`. Add:

```java
import java.util.Optional;
```

```java
    Optional<UserTypeEntity> findByCode(String code);
```

---

## Step 3: Seed the user types

The `ADMIN` row must exist **before** the first registration.

**New file:** `modules/user_type/data/user_type.json`

```json
[
  { "name": "Admin",    "code": "ADMIN",    "description": "Created at registration, full access", "active": true },
  { "name": "Employee", "code": "EMPLOYEE", "description": "Regular employee user",              "active": true }
]
```

**New file:** `modules/user_type/seeders/UserTypeSeeder.java`

```java
package com.codymitra.shared_service.modules.user_type.seeders;

import com.codymitra.shared_service.modules.user_type.entities.UserTypeEntity;
import com.codymitra.shared_service.modules.user_type.repositories.UserTypeRepository;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTypeSeeder implements Seeder {

    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/user_type/data/user_type.json";

    private final UserTypeRepository userTypeRepository;
    private final SeedDataReader seedDataReader;

    @Override
    public String name() {
        return "user_type";
    }

    @Override
    public int order() {
        return 5;
    }

    @Override
    public void seed() {
        seedDataReader.readList(DATA_FILE, UserTypeEntity.class).stream()
                .filter(userType -> !userTypeRepository.existsByCode(userType.getCode()))
                .forEach(userTypeRepository::save);
    }
}
```

Run once, from `codymitra-service/`:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--seed=user_type
```

---

## Step 4: Helper to load a user type

**New file:** `modules/user_type/services/UserTypeLookup.java`

```java
package com.codymitra.shared_service.modules.user_type.services;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.user_type.entities.UserTypeEntity;
import com.codymitra.shared_service.modules.user_type.enums.UserTypeCodeEnum;
import com.codymitra.shared_service.modules.user_type.repositories.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTypeLookup {

    private final UserTypeRepository userTypeRepository;

    public UserTypeEntity get(UserTypeCodeEnum code) {
        return userTypeRepository.findByCode(code.name()).orElseThrow(
                () -> new DataNotFoundException("User type " + code + " is not seeded")
        );
    }
}
```

If this throws, the `user_type` seeder hasn't been run.

---

## Step 5: Add `userType` to `UserEntity`

**File:** `modules/user/entities/UserEntity.java`

```java
import com.codymitra.shared_service.modules.user_type.entities.UserTypeEntity;
```

```java
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserTypeEntity userType;
```

Keep the `@Any` userable columns as **`nullable = false`**. Every user, including the registered admin,
is now linked to an employee.

Optional index for "users of this employee" lookups:

```java
@Table(name = "users", indexes = @Index(columnList = "userable_type, userable_id"))
```

> ⚠️ If `users` already has rows, `ddl-auto=update` can't add NOT NULL columns
> (`user_type_id`, `userable_type`, `userable_id`). In local dev, clear the table first:
> `truncate table users cascade;`

---

## Step 6: Update `registerUser` in auth-service

**File:** `auth-service/src/main/java/com/codymitra/auth_service/services/impl/AuthServiceImpl.java`

### 6a. New imports

```java
import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;
import com.codymitra.shared_service.modules.employee.enums.EmploymentTypeEnum;
import com.codymitra.shared_service.modules.employee.repositories.EmployeeRepository;
import com.codymitra.shared_service.modules.user_type.enums.UserTypeCodeEnum;
import com.codymitra.shared_service.modules.user_type.services.UserTypeLookup;
import org.springframework.transaction.annotation.Transactional;
```

### 6b. New dependencies (next to the existing `private final` fields)

```java
    private final EmployeeRepository employeeRepository;
    private final UserTypeLookup userTypeLookup;
```

### 6c. Replace `registerUser(...)`

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
        if (employeeRepository.existsByEmail(userRegisterDTO.email())) {
            throw new DataAlreadyExistsException("Employee already exists with this email");
        }

        String code = generateCode();

        // 1. employee record for the person registering
        EmployeeEntity employee = createEmployee(userRegisterDTO, code);

        // 2. user linked to that employee, role ADMIN
        UserEntity user = new UserEntity();
        user.setName(userRegisterDTO.name());
        user.setEmail(userRegisterDTO.email());
        user.setContactNo(userRegisterDTO.contactNo());
        user.setCountryCode(userRegisterDTO.countryCode());
        user.setAltContactNo(userRegisterDTO.altContactNo());
        user.setActive(true);
        user.setUsername(generateUsername(userRegisterDTO.name()));
        user.setCode(code);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.password()));

        user.setUserable(employee);
        user.setUserType(userTypeLookup.get(UserTypeCodeEnum.ADMIN));

        userRepository.save(user);

        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
        return new AuthResponseDTO(accessToken, refreshToken);
    }
```

### 6d. Add the private helper (next to `generateUsername` / `generateCode`)

```java
    private EmployeeEntity createEmployee(UserRegisterDTO dto, String code) {
        String[] nameParts = dto.name().trim().split("\\s+", 2);

        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeCode("EMP_" + code);
        employee.setFirstName(nameParts[0]);
        employee.setLastName(nameParts.length > 1 ? nameParts[1] : null);
        employee.setEmail(dto.email());
        employee.setContactNo(String.valueOf(dto.contactNo()));
        employee.setAltContactNo(dto.altContactNo() != null ? String.valueOf(dto.altContactNo()) : null);
        employee.setDateOfJoining(LocalDate.now());
        employee.setEmploymentType(EmploymentTypeEnum.FULL_TIME);
        employee.setStatus(EmployeeStatusEnum.ACTIVE);
        employee.setActive(true);
        return employeeRepository.save(employee);
    }
```

**Why each part:**
- **`@Transactional`:** the employee and the user are saved together. If saving the user fails, the employee is rolled back too, so you never get an orphan employee.
- **`employeeCode = "EMP_" + code`:** reuses the user's unique 6-digit code. `EmployeeMapper.resolveEmployeeCode()`
  builds the code from the name (`EMP_PRIYA_SHARMA`), which collides when two people share a name.
- **The employee is saved first:** `setUserable(employee)` needs the employee's id.
- **Contact numbers:** `EmployeeEntity` stores them as `String`, while the register DTO uses `Long`, hence `String.valueOf`.
- **`company`, `department`, `designation`:** left `null` (they're nullable). The admin fills these in later.

No change is needed in auth-service's `UserRegisterDTO`. The client can't choose its role or userable.

---

## Step 7: Remove the duplicate `register()` in shared-service

**File:** `modules/user/services/impl/UserServiceImpl.java` (and `UserService` + `modules/user/dtos/UserRegisterDTO.java`)

`UserServiceImpl.register(...)` is a second registration path that creates a user **without** an employee or user type.
That now fails on insert because those columns are NOT NULL. If nothing calls it (auth-service uses its own flow),
**delete it**: the method, its declaration in `UserService`, and the shared `UserRegisterDTO`.

Check that nothing calls it first:

```bash
grep -rn "userService.register\|\.register(" --include=*.java .
```

---

## Step 8 (optional): Show role + employee in the profile

**File:** `auth-service/.../mapper/AuthMapper.java` + `ProfileResponseDTO`. Add:

```java
        String userType,      // user.getUserType().getCode()
        String userableType,  // user.getUserableType()
        UUID userableId       // user.getUserableId()
```

> `userType` is LAZY and `spring.jpa.open-in-view=false`. Put `@Transactional(readOnly = true)` on
> `AuthServiceImpl.profile(...)` so the mapper can read `getUserType().getCode()` without a
> `LazyInitializationException`.

---

## Step 9: Test

1. Clear old data (dev only): `truncate table users cascade;`
2. Seed: `./mvnw spring-boot:run -Dspring-boot.run.arguments=--seed=user_type`
3. `POST /api/auth/register`:

   ```json
   {
     "name": "Priya Sharma",
     "email": "priya@example.com",
     "password": "secret123",
     "countryCode": "+91",
     "contactNo": 9876543210
   }
   ```

4. Check the DB:

   ```sql
   select e.id, e.employee_code, e.first_name, e.last_name from employees e;

   select u.name, ut.code as user_type, u.userable_type, u.userable_id
   from users u join user_types ut on ut.id = u.user_type_id;
   ```

   Expected: `ADMIN`, `EMPLOYEE`, and `userable_id` equal to the employee's `id`.

5. Register again with the same email → "User already exists with this email". The `employees` table must **not** get a second row.

---

## Checklist

1. [ ] `UserTypeCodeEnum` (ADMIN, EMPLOYEE)
2. [ ] `UserTypeRepository.findByCode`
3. [ ] `user_type.json` + `UserTypeSeeder`, then run `--seed=user_type`
4. [ ] `UserTypeLookup`
5. [ ] `UserEntity.userType` (`@ManyToOne`), userable stays NOT NULL
6. [ ] `AuthServiceImpl.registerUser`: `@Transactional`, create employee, link userable, set ADMIN
7. [ ] Delete `UserServiceImpl.register` + shared `UserRegisterDTO`
8. [ ] Optional: role + employee in the profile response
9. [ ] Test

**Next, after register works:** `UserServiceImpl.create()` (an admin creating other users for existing employees)
with `userableType` / `userableId` in `CreateUserDTO` and user type `EMPLOYEE`. See Steps 6–7 in [UserMorphSteps.md](UserMorphSteps.md).
