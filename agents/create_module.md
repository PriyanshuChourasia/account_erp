# create_module.md — How to Create a New Module in `shared-service`

This document is the single source of truth for scaffolding any new feature module
in **shared-service** (package-by-feature, not package-by-layer). Follow it exactly so
every module stays consistent and any LLM/agent can generate a compliant module.

---

## 1. Where modules live

```
shared-service/src/main/java/com/codymitra/shared_service/
├── entities/BaseEntity.java              ← shared audit base class (do NOT modify per-module)
├── exceptionHandler/                     ← shared; do NOT touch unless adding a new exception type
├── responseHandler/ResponseHandler.java  ← shared response wrapper; use, don't modify
└── modules/<feature_name>/               ← your module goes here (lowercase, snake_case)
```

Base package for all classes: `com.codymitra.shared_service.modules.<feature_name>`.

### Full module directory shape (complete CRUD + hierarchy example)

```
modules/<feature_name>/
├── controllers/
│   └── <Feature>Controller.java
├── dtos/
├── entities/
│   └── <Feature>Entity.java
├── enums/                             
├── mappers/
│   └── <Feature>Mapper.java
├── repositories/
│   └── <Feature>Repository.java
├── services/
│   ├── <Feature>Service.java            (interface)
│   └── impl/
│       └── <Feature>ServiceImpl.java
└── utils/                      
```

> If a layer is not needed yet, still create the empty class/interface file so the
> structure is uniform (see `party`/`designation` modules for the "skeleton" pattern).

---

## 2. Mandatory conventions

| Concern          | Rule |
|------------------|------|
| Feature folder   | `modules/<snake_case_feature>/` |
| Class prefix     | PascalCase feature name, e.g. `AccountGroup` → `AccountGroupEntity` |
| Controller route | `/` + plural snake_case, e.g. `/stock_categories`, `/units` |
| Entity table     | plural snake_case, e.g. `@Table(name = "stock_categories")` |
| Language level   | Java 25 (`spring-boot-starter-parent` 3.5.x, use modern syntax: records, streams) |
| Entity base      | Always `extends BaseEntity` with `@EqualsAndHashCode(callSuper = true)` |
| Lombok           | `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` on entities |
| Mappers          | `public final class` with `static` methods only; never a Spring bean |
| Service impl     | `@Service` + `@RequiredArgsConstructor` (constructor injection via `private final`) |
| DTOs             | Java `record` types; responses annotated `@JsonInclude(JsonInclude.Include.NON_NULL)` |
| Request DTOs     | Use `jakarta.validation` annotations, e.g. `@NotBlank(message = "Name is required")` |
| Business errors  | Throw `DataAlreadyExistsException` / `DataNotFoundException` (never raw exceptions) |
| Responses        | Always wrap through `ResponseHandler.generateResponse(...)` |
| No comments      | Do not add explanatory comments to generated code unless requested |
| Audit fields     | Never declare `createdBy/lastModifiedBy/createdAt/lastModifiedAt` — they come from `BaseEntity` |

### Entity id strategies in use
- `GenerationType.AUTO` — most entities (default choice)
- `GenerationType.IDENTITY` — `UnitEntity`
Either is acceptable; pick `AUTO` unless there is a reason.

---

## 3. Shared classes you must reuse (imports)

### BaseEntity (audit columns)
`com.codymitra.shared_service.entities.BaseEntity`
Provides: `createdBy`, `lastModifiedBy`, `createdAt`, `lastModifiedAt`. Extend it, don't re-declare.

### ResponseHandler
`com.codymitra.shared_service.responseHandler.ResponseHandler`
Three overloads — always use one:
```java
ResponseHandler.generateResponse(T data, String message, HttpStatus httpStatus)
ResponseHandler.generateResponse(List<T> data, String message, HttpStatus httpStatus)
ResponseHandler.generateResponse(String message, HttpStatus httpStatus)
```

### Exceptions
- `com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException`
- `com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException`

Both take a `String message`. They are already handled by `GlobalExceptionHandler`
(DataAlreadyExists → 400 BAD_REQUEST, DataNotFound → 404 NOT_FOUND). Throw them from
service code only.

---

## 4. File templates (copy, adapt names/fields)

Template below is for a feature called `<Feature>` with feature folder `<feature>`.

### 4.1 Entity — `entities/<Feature>Entity.java`
```java
package com.codymitra.shared_service.modules.<feature>.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "<feature>s")
@Table(name = "<feature>s")
@EqualsAndHashCode(callSuper = true)
public class <Feature>Entity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
```

Entity conventions:
- `@Entity(name=...)` and `@Table(name=...)` must match (plural snake_case).
- Name uniqueness via `@Column(unique = true)`.
- Hierarchy via `parent_id` as a plain `Long` (see `StockCategoryEntity`), NOT a `@ManyToOne` self join.
- Enums columns: `@Enumerated(EnumType.STRING)` + `@Column` (see `UnitEntity.unitType`).
- Use `org.hibernate.annotations.Comment` for SQL comments when helpful (see `UnitEntity`).
- The `entity` is a JPA `@Entity` — name it `<Feature>Entity`, keep it serialization-free (map to DTOs).

### 4.2 Repository — `repositories/<Feature>Repository.java`
```java
package com.codymitra.shared_service.modules.<feature>.repositories;

import com.codymitra.shared_service.modules.<feature>.entities.<Feature>Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface <Feature>Repository extends JpaRepository<<Feature>Entity, Long> {

    Boolean existsByName(String name);
    Boolean existsByCode(String code);
}
```
Add derived query methods as needed (`findByParentId`, `existsBy...`, etc.). Never write
custom SQL unless unavoidable.

### 4.3 Service interface — `services/<Feature>Service.java`
```java
package com.codymitra.shared_service.modules.<feature>.services;

import com.codymitra.shared_service.modules.<feature>.dtos.Create<Feature>Request;
import com.codymitra.shared_service.modules.<feature>.dtos.<Feature>DTO;

import java.util.List;

public interface <Feature>Service {

    List<<Feature>DTO> getAll();
    <Feature>DTO create(Create<Feature>Request request);
}
```
Use `String` return for "fire-and-forget" creates, or return the DTO when the caller needs
the persisted result (`UnitService.create` returns `UnitDTO`; `StockCategoryService.create`
returns `String`). Be consistent within a module.

### 4.4 Service implementation — `services/impl/<Feature>ServiceImpl.java`
```java
package com.codymitra.shared_service.modules.<feature>.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.<feature>.dtos.Create<Feature>Request;
import com.codymitra.shared_service.modules.<feature>.dtos.<Feature>DTO;
import com.codymitra.shared_service.modules.<feature>.entities.<Feature>Entity;
import com.codymitra.shared_service.modules.<feature>.mappers.<Feature>Mapper;
import com.codymitra.shared_service.modules.<feature>.repositories.<Feature>Repository;
import com.codymitra.shared_service.modules.<feature>.services.<Feature>Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class <Feature>ServiceImpl implements <Feature>Service {

    private final <Feature>Repository <feature>Repository;

    @Override
    public List<<Feature>DTO> getAll() {
        return <feature>Repository.findAll().stream().map(<Feature>Mapper::<feature>DTO).toList();
    }

    @Override
    public <Feature>DTO create(Create<Feature>Request request) {
        if (<feature>Repository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("<Feature> already exists");
        }
        <Feature>Entity entity = <Feature>Mapper.<feature>Entity(request);
        <Feature>Entity saved = <feature>Repository.save(entity);
        return <Feature>Mapper.<feature>DTO(saved);
    }
}
```
Service rules:
- Duplicate-name check first → `DataAlreadyExistsException`.
- Lookups that must exist → `.orElseThrow(() -> new DataNotFoundException("..."))`.
- Default `active = true` is set in the mapper, not here.

### 4.5 Mapper — `mappers/<Feature>Mapper.java`
```java
package com.codymitra.shared_service.modules.<feature>.mappers;

import com.codymitra.shared_service.modules.<feature>.dtos.Create<Feature>Request;
import com.codymitra.shared_service.modules.<feature>.dtos.<Feature>DTO;
import com.codymitra.shared_service.modules.<feature>.entities.<Feature>Entity;

public final class <Feature>Mapper {

    public static <Feature>DTO <feature>DTO(<Feature>Entity entity) {
        return new <Feature>DTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getAlias(),
                entity.getParentId(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static <Feature>Entity <feature>Entity(Create<Feature>Request request) {
        <Feature>Entity entity = new <Feature>Entity();
        entity.setName(request.name());
        if (request.code() != null) {
            entity.setCode(request.code().toUpperCase());
        }
        entity.setAlias(request.alias());
        entity.setDescription(request.description());
        entity.setParentId(request.parentId());
        entity.setActive(true);
        return entity;
    }
}
```
Mapper rules:
- `public final class`, all `static` methods.
- Request → Entity maps to the exact field set of the request DTO.
- Entity → DTO is a positional `record` construction — order must match the DTO record.
- Codes are stored upper-case (`request.code().toUpperCase()`), or generated from the name
  when no code is supplied (see `UnitMapper.unitEntity`).
- For compound mappings that join related rows, accept extra params
  (see `UnitMapper.unitDTO(unit, baseUnit1, baseUnit2)`).

### 4.6 Controller — `controllers/<Feature>Controller.java`
```java
package com.codymitra.shared_service.modules.<feature>.controllers;

import com.codymitra.shared_service.modules.<feature>.dtos.Create<Feature>Request;
import com.codymitra.shared_service.modules.<feature>.dtos.<Feature>DTO;
import com.codymitra.shared_service.modules.<feature>.services.<Feature>Service;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/<feature>s")
@RequiredArgsConstructor
public class <Feature>Controller {

    private final <Feature>Service <feature>Service;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<<Feature>DTO> dtos = <feature>Service.getAll();
        String message = dtos.size() + " total <feature>s fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Create<Feature>Request request) {
        <Feature>DTO dto = <feature>Service.create(request);
        return ResponseHandler.generateResponse(dto, "<Feature> created successfully", HttpStatus.CREATED);
    }
}
```
Controller rules:
- `@RestController` + `@RequestMapping(path = "/<plural_snake_case>")`.
- `@RequiredArgsConstructor` + `private final <Feature>Service`.
- `@Valid` on request bodies; wrap every return in `ResponseHandler.generateResponse`.
- No business logic in the controller — delegate to the service.

### 4.7 Request DTO — `dtos/Create<Feature>Request.java`
```java
package com.codymitra.shared_service.modules.<feature>.dtos;

import jakarta.validation.constraints.NotBlank;

public record Create<Feature>Request(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        String alias,
        Long parentId,
        String description
) {
}
```
- Request DTOs are Java records named `Create<Feature>Request` (or `...RequestDTO`,
  e.g. `CreateUnitRequestDTO` — both exist; prefer `Create<Feature>Request` for new modules).
- Only require what is mandatory (`name`); keep everything else optional/nullable.

### 4.8 Response DTO — `dtos/<Feature>DTO.java`
```java
package com.codymitra.shared_service.modules.<feature>.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record <Feature>DTO(
        Long id,
        String name,
        String code,
        String alias,
        Long parentId,
        String description,
        Boolean active
) {
}
```
- Always `@JsonInclude(JsonInclude.Include.NON_NULL)` so empty fields are omitted.
- Field order in the record MUST match the mapper's positional constructor call.

---

## 5. Hierarchy (tree) pattern — only if the feature is parent/child

Copy this pattern from `stock_category` (the canonical example). Add:

### `dtos/<Feature>HierarchyDTO.java`
```java
package com.codymitra.shared_service.modules.<feature>.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record <Feature>HierarchyDTO(
        Long id,
        String name,
        String code,
        String alias,
        Long parentId,
        String description,
        Boolean isActive,
        List<<Feature>HierarchyDTO> children
) {
}
```
> Note: the existing `StockCategoryHierarchyDTO` uses `isActive` (not `active`) for the
> Boolean — keep that quirk when copying this template, or normalize to `active`.

### `utils/<Feature>Hierarchy.java`
Build the tree with a `Map<Long, <Feature>HierarchyDTO>` and a single pass:
1. Map every entity id → a DTO with an empty `children` list.
2. Walk entities again; `parentId == null` ⇒ root, otherwise append to parent's `children`
   (see `StockCategoryHierarchy.stockCategoryDTO` for the exact implementation).

### Service + controller additions
```java
// service interface
List<<Feature>HierarchyDTO> getAllWithChildren();

// service impl
List<<Feature>HierarchyDTO> getAllWithChildren() {
    return <Feature>Hierarchy.<feature>DTO(<feature>Repository.findAll());
}

// controller
@GetMapping("/all-<feature>-tree")
public ResponseEntity<Map<String, Object>> getAllTree() { /* ... */ }
```

---

## 6. Reference: the canonical existing modules

Read these before writing anything new — they are the ground truth for the conventions above:

| Purpose                          | Module                 | What to look at |
|----------------------------------|------------------------|-----------------|
| Full CRUD + DTO + compound map   | `unit`                 | `UnitEntity`, `UnitRepository`, `UnitService`/`impl`, `UnitController`, `UnitMapper`, `dtos/`, `enums/UnitTypeEnum` |
| Hierarchy/tree feature           | `stock_category`       | `StockCategoryEntity`, `StockCategoryHierarchy`, `StockCategoryHierarchyDTO`, service/controller tree endpoints |
| Group-style feature with flags   | `stock_group`          | `StockGroupEntity`, `StockGroupMapper`, `StockGroupDTO`, `CreateStockRequest` |
| Skeleton (empty layers)          | `party`, `designation` | Bare `Controller`/`Service`/`Mapper`/`Repository` placeholders |
| Accounting-linked entity         | `account_ledger`       | `AccountLedgerEntity` (uses Hibernate `@Any` + `Ledgerable` interface) |
| Accounting group (simple entity) | `account_group`        | `AccountGroupEntity` (name, code, parent_id, description, active) |

---

## 7. Do NOT do

- ❌ Add `controllers/`, `services/`, etc. at the shared root — modules own their layers.
- ❌ Put business rules in controllers or entities.
- ❌ Catch-and-return exceptions inside services (let `GlobalExceptionHandler` handle them).
- ❌ Use `@Autowired` field injection — always constructor injection via `@RequiredArgsConstructor`.
- ❌ Name packages/classes with hyphens — Java package segments must be valid identifiers.
- ❌ Hand-write audit columns; extend `BaseEntity`.
- ❌ Use `List.of(...)`, `Arrays.asList(...)`, etc. instead of the mapper/record pattern.
- ❌ Skip the `@JsonInclude(NON_NULL)` on response DTOs.
- ❌ Forget: `@Entity(name)` must equal `@Table(name)`.

---

## 8. Verification checklist

Before finishing a new module, confirm:

- [ ] Feature folder is `modules/<snake_case>/` under the shared base package.
- [ ] Entity `extends BaseEntity` with `@EqualsAndHashCode(callSuper = true)`, Lombok triple, `@Entity`/`@Table` names match.
- [ ] Repository extends `JpaRepository` and is annotated `@Repository`.
- [ ] Service interface + `impl` (annotated `@Service`, `@RequiredArgsConstructor`) exist.
- [ ] Mapper is `public final`, static methods only, positional constructor order matches records.
- [ ] Controller routes are `/` + plural snake_case; uses `ResponseHandler.generateResponse`; `@Valid` on bodies.
- [ ] Request DTO has `@NotBlank` on required fields; response DTO has `@JsonInclude(NON_NULL)`.
- [ ] Throws only `DataAlreadyExistsException` / `DataNotFoundException` for business errors.
- [ ] No stray comments, no dead imports.

### Build command
Modules build under the root reactor (Java 25 — use `.sdkmanrc`/sdkman to get `java=25.0.3-tem`):
```
mvn -pl shared-service -am clean install
```
