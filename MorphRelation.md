# Polymorphic (Morph) Relations in Spring Boot

This is the JPA/Hibernate version of Laravel's `morphTo` / `morphMany`: one table stores a `userable_id` and a `userable_type`, and each row can point to a different entity type.

The project uses Spring Boot `3.5.x`, which ships with Hibernate 6.x, so the Hibernate `@Any` mapping below is available.

> **Naming:** don't call the column field `id`. The entity already uses `id` for its own primary key. Use `userableId`.

---

## 1. Owning side (`morphTo`) with `@Any`

### Marker interface

Every entity that can be "userable" implements this interface:

```java
public interface Userable {
    UUID getId();
}
```

### Target entities

```java
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity implements Userable {
    @Id @GeneratedValue
    private UUID id;
    // ...
}

@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity implements Userable {
    @Id @GeneratedValue
    private UUID id;
    // ...
}
```

### Entity holding the morph columns

```java
import org.hibernate.annotations.*;
import jakarta.persistence.*;

@Entity
@Table(
    name = "accounts",
    indexes = @Index(columnList = "userable_type, userable_id")
)
@Data
public class AccountEntity extends BaseEntity {

    @Id @GeneratedValue
    private UUID id;

    @Any(fetch = FetchType.LAZY)
    @AnyDiscriminator(DiscriminatorType.STRING)
    @AnyDiscriminatorValue(discriminator = "USER",     entity = UserEntity.class)
    @AnyDiscriminatorValue(discriminator = "EMPLOYEE", entity = EmployeeEntity.class)
    @AnyKeyJavaClass(UUID.class)
    @Column(name = "userable_type", nullable = false)   // discriminator column
    @JoinColumn(name = "userable_id", nullable = false) // id of the target row
    private Userable userable;

    // Optional: read-only access to the raw columns, useful for queries
    @Column(name = "userable_id", insertable = false, updatable = false)
    private UUID userableId;

    @Column(name = "userable_type", insertable = false, updatable = false)
    private String userableType;
}
```

- `@AnyDiscriminatorValue` works like Laravel's `Relation::morphMap()`. It stores short names (`USER`, `EMPLOYEE`) instead of class names.
- If Postgres complains about the key column type, add `@AnyKeyJdbcTypeCode(SqlTypes.UUID)`.

### Usage

```java
AccountEntity acc = new AccountEntity();
acc.setUserable(employee);   // writes userable_type='EMPLOYEE', userable_id=<employee.id>
accountRepository.save(acc);

Userable owner = acc.getUserable(); // returns UserEntity or EmployeeEntity
if (owner instanceof EmployeeEntity e) {
    // ...
}
```

---

## 2. Inverse side (`morphMany`)

JPA has no `mappedBy` for `@Any`, so you can't put a `@OneToMany` on `UserEntity`. Query through the repository instead:

```java
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    List<AccountEntity> findByUserableTypeAndUserableId(String userableType, UUID userableId);
}
```

```java
// equivalent of: $user->accounts
accountRepository.findByUserableTypeAndUserableId("USER", user.getId());
```

Keep the type strings in one enum or constants class so they always match the `@AnyDiscriminatorValue` mappings:

```java
public final class UserableTypes {
    public static final String USER = "USER";
    public static final String EMPLOYEE = "EMPLOYEE";
    private UserableTypes() {}
}
```

---

## 3. Caveats

- **No database foreign key.** As in Laravel, Postgres can't enforce `userable_id` against several tables. Deletes won't cascade, so clean up child rows in the service layer.
- **No JPQL joins through `userable`.** You can't write `join a.userable u where u.name = ...`. Filter on `userableType` and `userableId`, or query each concrete type separately.
- **Index it.** `ddl-auto=update` builds the schema, so declare the `(userable_type, userable_id)` index on the entity, as shown above.

---

## 4. Alternative: manual resolution, no Hibernate-specific annotations

Keep only the two plain columns and resolve the target yourself:

```java
@Column(name = "userable_id", nullable = false)
private UUID userableId;

@Column(name = "userable_type", nullable = false)
private String userableType;
```

```java
@Service
@RequiredArgsConstructor
public class UserableResolver {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    public Optional<? extends Userable> resolve(String type, UUID id) {
        return switch (type) {
            case UserableTypes.USER -> userRepository.findById(id);
            case UserableTypes.EMPLOYEE -> employeeRepository.findById(id);
            default -> throw new IllegalArgumentException("Unknown userable type: " + type);
        };
    }
}
```

This takes more code, but it avoids Hibernate-specific mapping and is easy to debug.

---

## Where it goes in this project

The `Userable` interface and the entities belong in `shared-service`, following the existing layout `modules/<feature>/entities/<Feature>Entity.java`. Entities extend `BaseEntity` with `@EqualsAndHashCode(callSuper = true)`.
