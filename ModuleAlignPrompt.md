# Prompt: Align any shared-service module with its entity (reusable)

> Fill in the three placeholders, then copy everything below the line and give it to the LLM / coding agent.
>
> | Placeholder        | Example                     |
> |--------------------|-----------------------------|
> | `{{MODULE}}`       | `employee_work_unit`        |
> | `{{ENTITY}}`       | `EmployeeWorkUnitEntity`    |
> | `{{REFERENCE}}`    | `employee_category` (a module that is already correct) |
>
> Optional: add a "Module-specific notes" section at the end for anything special
> (hierarchy via `parentId`, foreign keys, enums, delete guards, seed data).

---

You are working in a Spring Boot 3.5 + Hibernate 6 + Lombok project (`accountErpApi`). The entity
`{{ENTITY}}` has changed, and the rest of its module is out of date. Update every layer of the module —
DTOs → mapper → repository → service → service impl → controller (and seed JSON if present) — so it matches the
entity exactly and follows the conventions of the reference module.

- Module: `shared-service/src/main/java/com/codymitra/shared_service/modules/{{MODULE}}/`
- Reference: `shared-service/src/main/java/com/codymitra/shared_service/modules/{{REFERENCE}}/`

## Step 1 — Read before writing

1. Read `{{MODULE}}/entities/{{ENTITY}}.java` and `shared_service/entities/BaseEntity.java`.
   Write down every field: name, Java type, column name, `nullable`, `unique`, relations (`@ManyToOne`, `@OneToOne`,
   UUID foreign-key columns), enums. **This entity is the source of truth. Do not modify it.**
2. Read every file in the reference module `{{REFERENCE}}/` to learn the house style.
3. Read every file in `{{MODULE}}/` (including `data/*.json`, `enums/`, `utils/`, `seeders/` if they exist).
4. Grep the whole repo for the module's classes to find callers outside the module:
   `grep -rn '<EntityPrefix>' --include='*.java' shared-service codymitra-service auth-service`
   If callers exist, update them to match, and list them in your final report.

## Step 2 — Audit

Before editing, list every mismatch you find. Typical ones:
- Fields in DTO / request / mapper / service that no longer exist on the entity (renamed or removed), or entity
  fields missing from them.
- Wrong types (e.g. a module-local enum where the entity uses a shared one, `String` where the entity uses an enum).
- Calls to methods that don't exist (`builder()` on a record, getters for removed fields, wrong constructor arity).
- Duplicate or misnamed files (e.g. a second interface with the same name in another file) — delete them.
- Mapping done inline in the service instead of in the mapper.
- Raw `RuntimeException`, `Optional` leaking to the controller, missing uniqueness checks.
- Controller not using `ResponseHandler`, missing `@Valid`, route names that differ from other modules.
- Annotations on the wrong thing (`@Service` on an interface, class-level `@Transactional`).

## Step 3 — Apply the conventions

### DTO (`dtos/<Feature>DTO.java`)
- Java `record`, annotated `@JsonInclude(JsonInclude.Include.NON_NULL)`.
- `UUID id` first, then entity fields in entity order, `active` last.
- Relations are exposed as a nested small DTO or as the related id — follow whatever the reference/similar module does.

### Request (`dtos/Create<Feature>Request.java`)
- Java `record`, used for both create and update.
- No `id`, no `active`, no audit fields.
- Every entity column with `nullable = false` gets validation: `@NotBlank` for `String`, `@NotNull` for others —
  **unless** the mapper derives or defaults it (e.g. a code generated from the name, a `Boolean` that defaults to
  `false`). Message format: `"<Field> is required"`.
- Relations are passed as `UUID <relation>Id`.

### Mapper (`mappers/<Feature>Mapper.java`)
- `public final class`, static methods only:
  - `<feature>DTO(entity)` → DTO
  - `<feature>Entity(request)` → new entity via `applyRequest`, then `setActive(true)`
  - `<feature>Entity(entity, request)` → `applyRequest` only (never touches `active`)
  - `private static applyRequest(entity, request)` — the only place request fields are copied onto the entity.
- Put normalisation helpers here as `public static` (e.g. `resolveCode`, `resolveAlias`) so the service can reuse
  the exact same value for uniqueness checks. Trim strings; turn blank optional values into `null`.
- Related entities are resolved in the service and passed into the mapper as extra parameters.

### Repository (`repositories/<Feature>Repository.java`)
- `@Repository`, `extends JpaRepository<{{ENTITY}}, UUID>`.
- For every `unique = true` column: `Boolean existsByX(...)` and `Boolean existsByXAndIdNot(..., UUID id)`.
- Add `findByCode` / `findByX` only when something uses it (e.g. a seeder). Remove unused methods.

### Service interface (`services/<Feature>Service.java`)
- Plain interface, no annotations:
```java
List<FeatureDTO> getAll();
FeatureDTO getById(UUID id);
FeatureDTO create(CreateFeatureRequest request);
FeatureDTO update(UUID id, CreateFeatureRequest request);
String delete(UUID id);
```

### Service impl (`services/impl/<Feature>ServiceImpl.java`)
- `@Service @RequiredArgsConstructor`, `private final` repository fields named in full
  (`employeeCategoryRepository`, not `repository`).
- No class-level `@Transactional`. Reads: `@Transactional(readOnly = true)`; writes: `@Transactional`
  (`spring.jpa.open-in-view=false`, so any read that touches lazy relations must be transactional).
- All entity↔DTO mapping goes through the mapper.
- Private `findById(UUID id)` throwing `DataNotFoundException("No such <feature> found with this id")`.
- Private `validateUnique(..., UUID id)`: `id == null` → `existsByX`, otherwise `existsByXAndIdNot`;
  throw `DataAlreadyExistsException("<Feature> already exists with this <field>")`. Skip the check for optional
  unique values that are `null`.
- Related ids from the request: load them, throw `DataNotFoundException` if missing.
- `delete` returns `"<Feature> deleted successfully"`. If other tables reference this entity, check first and throw
  `IllegalArgumentException("<Feature> is in use ...")` (returns 400) instead of letting a FK error surface.
- Invalid client input → `IllegalArgumentException`.
- Exceptions live in `com.codymitra.shared_service.exceptionHandler.exceptions`.

### Controller (`controllers/<Feature>Controller.java`)
- `@RestController @RequestMapping(path = "/<plural_snake_case>") @RequiredArgsConstructor`
  — check the reference module and keep route style consistent.
- Every method returns `ResponseEntity<Map<String, Object>>` via
  `com.codymitra.shared_service.responseHandler.ResponseHandler.generateResponse(...)`.
- `@Valid @RequestBody` on every body.

| Method | Path      | Status | Message                                   |
|--------|-----------|--------|-------------------------------------------|
| GET    | `/list`   | 200    | `dtos.size() + " total <features> fetched"` |
| GET    | `/{id}`   | 200    | `"<Feature> fetched successfully"`        |
| POST   | `/create` | 201    | `"<Feature> created successfully"`        |
| PUT    | `/{id}`   | 200    | `"<Feature> updated successfully"`        |
| DELETE | `/{id}`   | 200    | message returned by the service           |

### Seed data (`data/*.json`) and seeder (if present)
- JSON keys must match the entity's field names.
- Never put UUIDs in seed JSON; reference other rows by `code` / `name` (e.g. `parentCode`, `primaryUnitCode`).
- An existing seeder implements `com.codymitra.shared_service.seeders.Seeder` and must stay idempotent
  (skip rows that already exist). Do not create a new seeder unless asked.

### Hierarchies (only if the entity has `parentId`)
- Parse/validate `parentId` in the mapper (blank → `null`, bad UUID → `IllegalArgumentException`).
- In the service, call `validateParent(parentId, id)` on create (`id = null`) and update: reject self-parent,
  missing parent, and cycles (walk up the parent chain).
- Block delete while children exist.

## Step 4 — Rules

- Do **not** modify the entity, `BaseEntity`, shared enums, or other modules' files (except callers found in Step 1).
- Match surrounding code: Lombok, records, static mappers, 4-space indentation, very few comments.
- Delete files and enums that become unused; remove unused imports.
- Don't add features that weren't asked for (no pagination, no new endpoints beyond the table, no new seeders).
- If the entity has an ambiguity (e.g. a module-local enum exists but the entity uses a different one), follow the
  entity and mention the ambiguity in your report.

## Step 5 — Verify

JDK 25 may not be installed locally; compile with JDK 21:
```bash
JAVA_HOME=$(/usr/libexec/java_home -v 21) mvn -q -pl shared-service -am compile -Dmaven.compiler.release=21
```
It must succeed with zero errors. Then grep the module for every removed/renamed field from your Step 2 audit and
confirm there are no matches.

If a new `nullable = false` column was added to an existing table, note that `ddl-auto=update` will fail on a table
with existing rows, and give the `alter table ... add column ... not null default ...` SQL in your report.

## Step 6 — Report

List each file changed / created / deleted with a one-line summary, any callers outside the module you updated,
any SQL the user must run, and any ambiguity you resolved.

## Module-specific notes

{{Optional — e.g. "unitType uses shared enums.UnitTypeEnum", "symbol is case-sensitive", "block delete if employees use it"}}
