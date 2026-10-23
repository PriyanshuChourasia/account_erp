# User → Userable morph relation: remaining steps

A user belongs to one "userable" entity (Employee for now, Customer etc. later), stored as
`users.userable_type` + `users.userable_id`. Background: [MorphRelation.md](MorphRelation.md).

All paths below are relative to
`shared-service/src/main/java/com/codymitra/shared_service/`.

---

## Status

| # | Step | Status |
|---|------|--------|
| 1 | `modules/user/interfaces/Userable.java` | ✅ Done |
| 2 | `EmployeeEntity implements Userable` | ✅ Done |
| 3 | `@Any` mapping in `UserEntity` | ✅ Done (still uses the literal `"Employee"`) |
| 4 | `UserableTypeEnum` (with nested `Values` constants) | ⬜ To create |
| 5 | Finish `UserableResolver` | ⚠️ Half-written, **does not compile yet** |
| 6 | Add `userableType` / `userableId` to `CreateUserDTO` | ⬜ To do |
| 7 | Set the userable in `UserServiceImpl.create()` | ⬜ To do |
| 8 | Handle `register()` (no userable today) | ⬜ Decide + do |
| 9 | Expose userable in `UserDTO` + `UserMapper` | ⬜ To do |
| 10 | Repository query for "users of this employee" | ⬜ To do |
| 11 | Optional: change userable in `UpdateUserDTO` | ⬜ Optional |
| 12 | Test | ⬜ To do |

---

## Step 4: Create `UserableTypeEnum`

**New file:** `modules/user/enums/UserableTypeEnum.java`

```java
package com.codymitra.shared_service.modules.user.enums;

public enum UserableTypeEnum {

    EMPLOYEE(Values.EMPLOYEE);

    // compile-time constants, needed because annotations cannot read enum values
    public static final class Values {
        public static final String EMPLOYEE = "Employee";
        private Values() {}
    }

    private final String value;

    UserableTypeEnum(String value) { this.value = value; }

    public String getValue() { return value; }
}
```

**Update** `modules/user/entities/UserEntity.java`. Replace the literal string:

```java
@AnyDiscriminatorValue(discriminator = UserableTypeEnum.Values.EMPLOYEE, entity = EmployeeEntity.class)
```

> ⚠️ The value `"Employee"` is what gets stored in the DB. Never rename it once rows exist.

---

## Step 5: Finish `UserableResolver`

**File:** `modules/user/services/UserableResolver.java`. Replace the half-written `switch`:

```java
package com.codymitra.shared_service.modules.user.services;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.employee.repositories.EmployeeRepository;
import com.codymitra.shared_service.modules.user.enums.UserableTypeEnum;
import com.codymitra.shared_service.modules.user.interfaces.Userable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserableResolver {

    private final EmployeeRepository employeeRepository;

    public Userable resolve(UserableTypeEnum type, UUID id) {
        return switch (type) {
            case EMPLOYEE -> employeeRepository.findById(id).orElseThrow(
                    () -> new DataNotFoundException("Employee does not exist with this id")
            );
        };
    }
}
```

Because the `switch` covers the enum exhaustively, Java fails to compile if you add a new enum value and forget its `case`.

---

## Step 6: Add the fields to `CreateUserDTO`

**File:** `modules/user/dtos/CreateUserDTO.java`. Add:

```java
import com.codymitra.shared_service.modules.user.enums.UserableTypeEnum;
import java.util.UUID;
```

```java
        @NotNull(message = "Userable type is required")
        UserableTypeEnum userableType,

        @NotNull(message = "Userable id is required")
        UUID userableId
```

Request body example:

```json
{
  "name": "Ravi",
  "email": "ravi@example.com",
  "contactNo": 9876543210,
  "password": "secret",
  "userableType": "EMPLOYEE",
  "userableId": "7f3c...-uuid"
}
```

> Jackson reads the enum by its **name** (`"EMPLOYEE"`), not by `getValue()` (`"Employee"`).
> The API uses `EMPLOYEE` and the DB stores `Employee`. That is fine, because the enum converts between them.

---

## Step 7: Set the userable in `UserServiceImpl.create()`

**File:** `modules/user/services/impl/UserServiceImpl.java`

1. Inject the resolver:

```java
    private final UserableResolver userableResolver;
```

2. In `create(...)`, after `UserEntity entity = UserMapper.userEntity(createUserDTO);`:

```java
        entity.setUserable(
                userableResolver.resolve(createUserDTO.userableType(), createUserDTO.userableId())
        );
```

Hibernate then writes `userable_type = 'Employee'` and `userable_id = <employee id>` on save.

---

## Step 8: Decide what happens in `register()`

> **Decided:** registered users are **ADMIN** with no userable (option B + a `user_type` relation).
> Follow [UserTypeAdminChanges.md](UserTypeAdminChanges.md) instead of this step.

`UserServiceImpl.register(UserRegisterDTO)` creates a user **without** a userable, but `UserEntity`
maps `userable_type` / `userable_id` as `nullable = false`. **Registration will fail on insert** unless you pick one:

- **A. Registration also requires a userable.** Add `userableType` + `userableId` to
  `modules/user/dtos/UserRegisterDTO.java` **and** to auth-service's own `UserRegisterDTO`
  (`auth-service/.../dtos/UserRegisterDTO.java`), then call `entity.setUserable(...)` as in Step 7.
- **B. A user can exist without a userable.** Change both `@Column` / `@JoinColumn` in `UserEntity` to
  `nullable = true`, and link the userable later.

> Also: if the `users` table already has rows, `ddl-auto=update` cannot add NOT NULL columns to them.
> Either clear those rows, or use `nullable = true` (option B).

---

## Step 9: Return the userable in the API response

**File:** `modules/user/dtos/UserDTO.java`. Add two fields:

```java
        String userableType,
        UUID userableId
```

**File:** `modules/user/mappers/UserMapper.java`, in `userDTO(...)`. Add these after `entity.getActive()` (same order as the record):

```java
                entity.getUserableType(),
                entity.getUserableId()
```

Use the read-only `userableType` / `userableId` columns here, **not** `entity.getUserable()`.
That avoids lazily loading the employee for every user in a list (an N+1 query problem).

---

## Step 10: Query "users of this employee" (inverse side)

JPA has no `mappedBy` for `@Any`, so there is no `employee.getUsers()`. Add to
`modules/user/repositories/UserRepository.java`:

```java
    List<UserEntity> findByUserableTypeAndUserableId(String userableType, UUID userableId);

    Boolean existsByUserableTypeAndUserableId(String userableType, UUID userableId);
```

Usage:

```java
userRepository.findByUserableTypeAndUserableId(UserableTypeEnum.EMPLOYEE.getValue(), employeeId);
```

Optional: in `create()`, use `existsBy...` to block creating two users for the same employee.

---

## Step 11 (optional): Allow changing the userable on update

Only if needed. Add `UserableTypeEnum userableType` + `UUID userableId` (both nullable) to
`UpdateUserDTO`, and in `UserServiceImpl.update()`:

```java
        if (updateUserDTO.userableType() != null && updateUserDTO.userableId() != null) {
            entity.setUserable(
                    userableResolver.resolve(updateUserDTO.userableType(), updateUserDTO.userableId())
            );
        }
```

---

## Step 12: Test

1. Build: `mvn -pl codymitra-service -am clean install`
2. Run codymitra-service and check the startup log. The `users` table should get `userable_type` / `userable_id`.
3. Create an employee, then `POST` a user with `userableType: "EMPLOYEE"` and that employee's id.
4. Check the DB: `select name, userable_type, userable_id from users;` should show `Employee` and the employee id.
5. Negative cases: random `userableId` → "Employee does not exist"; missing type → validation error.

---

## Adding a new userable type later (e.g. Customer)

1. `UserableTypeEnum`: add `CUSTOMER(Values.CUSTOMER)` and `public static final String CUSTOMER = "Customer";`
2. `CustomerEntity`: add `implements Userable`
3. `UserEntity`: add `@AnyDiscriminatorValue(discriminator = UserableTypeEnum.Values.CUSTOMER, entity = CustomerEntity.class)`
4. `UserableResolver`: inject `CustomerRepository`, then add `case CUSTOMER -> ...` (the compiler will remind you)

## Things to remember

- No DB foreign key on `userable_id`. Deleting an employee does **not** delete or block its users; handle that in `EmployeeService.delete()`.
- You can't JPQL-join through `userable`. Filter on `userableType` / `userableId` instead.
- Add an index on `(userable_type, userable_id)` in `UserEntity`'s `@Table(indexes = ...)` for Step 10 queries.
