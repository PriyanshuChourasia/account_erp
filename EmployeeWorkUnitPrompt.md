# Prompt: Align the `employee_work_unit` module with `EmployeeWorkUnitEntity`

> Copy everything below this line and give it to the LLM / coding agent.

---

You are working in a Spring Boot 3.5 + Hibernate 6 + Lombok project (`accountErpApi`). Your task is to fix the
`employee_work_unit` module in **shared-service** so that every layer (DTOs → mapper → repository → service →
controller) matches its entity and follows the same conventions as the rest of the codebase.

Module path:
`shared-service/src/main/java/com/codymitra/shared_service/modules/employee_work_unit/`

## 1. Source of truth — do NOT modify this file

`entities/EmployeeWorkUnitEntity.java` (extends `BaseEntity`, which provides `UUID id`, audit and soft-delete columns):

| Field         | Java type                                         | Column        | Constraints                  |
|---------------|---------------------------------------------------|---------------|------------------------------|
| `unitType`    | `com.codymitra.shared_service.enums.UnitTypeEnum` (`SIMPLE`, `COMPOUND`) | `unit_type` | `nullable = false`, `@Enumerated(STRING)` |
| `name`        | `String`                                          | `name`        | `nullable = false`, unique   |
| `symbol`      | `String`                                          | `symbol`      | `nullable = false`, unique   |
| `description` | `String`                                          | `description` | nullable                     |
| `active`      | `Boolean`                                         | `active`      | nullable                     |

There is **no** `code`, `workUnitType` or `parentId` field. Any code referencing those must be removed or replaced.

## 2. Reference module to copy the style from

Use `modules/employee_category/` as the template. Read all of its files first and mirror its structure:
- `controllers/EmployeeCategoryController.java`
- `dtos/CreateEmployeeCategoryRequest.java`, `dtos/EmployeeCategoryDTO.java`
- `mappers/EmployeeCategoryMapper.java`
- `repositories/EmployeeCategoryRepository.java`
- `services/EmployeeCategoryService.java`, `services/impl/EmployeeCategoryServiceImpl.java`

## 3. Current problems you must fix

1. `services/StockUnitService.java` is a misnamed duplicate that declares a second `EmployeeWorkUnitService`
   interface in the same package, so the module does not compile. **Delete this file.**
2. DTO, request and mapper use `WorkUnitTypeEnum workUnitType`, but the entity has `UnitTypeEnum unitType`.
3. `EmployeeWorkUnitServiceImpl` calls methods that do not exist: `EmployeeWorkUnitDTO.builder()` (it is a record),
   `entity.getWorkUnitType()`, `getCode()`/`setCode()`, `request.code()`, `getParentId()`, and builds a DTO with 7 args.
4. The service impl duplicates mapping logic instead of using `EmployeeWorkUnitMapper`.
5. The service impl throws a raw `RuntimeException` and has no uniqueness checks for `name` / `symbol`.
6. `services/EmployeeWorkUnitService.java` (an interface) is annotated with `@Service` — remove it.
7. The controller returns raw `ResponseEntity<DTO>` / `List<DTO>` instead of `ResponseHandler.generateResponse(...)`,
   uses `Optional` + `notFound()`, has no `@Valid`, and uses different route names from the other modules.

## 4. Required end state, file by file

### `enums/WorkUnitTypeEnum.java`
Delete it once nothing references it (the entity uses the shared `enums.UnitTypeEnum`).

### `dtos/EmployeeWorkUnitDTO.java`
```java
@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeeWorkUnitDTO(
        UUID id,
        UnitTypeEnum unitType,
        String name,
        String symbol,
        String description,
        Boolean active
) {}
```

### `dtos/CreateEmployeeWorkUnitRequest.java`
- `@NotBlank(message = "Name is required") String name`
- `@NotBlank(message = "Symbol is required") String symbol`
- `@NotNull(message = "Unit type is required") UnitTypeEnum unitType`
- `String description`

(`symbol` and `unitType` are `nullable = false` in the entity, so they must be validated here.)

### `mappers/EmployeeWorkUnitMapper.java`
Keep the existing shape (`final` class, static methods, private `applyRequest`):
- `employeeWorkUnitDTO(entity)` → maps the 6 DTO fields using `getUnitType()`.
- `employeeWorkUnitEntity(request)` → new entity via `applyRequest`, then `setActive(true)`.
- `employeeWorkUnitEntity(entity, request)` → `applyRequest` only (do not touch `active`).
- Add `public static String resolveSymbol(String symbol)` → `symbol.trim()` (keep the user's casing; units like
  `Hr`, `Day` are case-meaningful).
- `applyRequest` sets `name` (trimmed), `unitType`, `symbol` (via `resolveSymbol`), `description`.

### `repositories/EmployeeWorkUnitRepository.java`
Keep `@Repository`, `JpaRepository<EmployeeWorkUnitEntity, UUID>`. Use `Boolean` return types to match other
repositories. Required methods:
`existsByName`, `existsBySymbol`, `existsByNameAndIdNot`, `existsBySymbolAndIdNot`, `findBySymbol`.
Remove `findByActiveTrue` and `findByName` if they end up unused.

### `services/EmployeeWorkUnitService.java`
Plain interface (no annotations), same method names as `EmployeeCategoryService`:
```java
List<EmployeeWorkUnitDTO> getAll();
EmployeeWorkUnitDTO getById(UUID id);
EmployeeWorkUnitDTO create(CreateEmployeeWorkUnitRequest request);
EmployeeWorkUnitDTO update(UUID id, CreateEmployeeWorkUnitRequest request);
String delete(UUID id);
```

### `services/impl/EmployeeWorkUnitServiceImpl.java`
Mirror `EmployeeCategoryServiceImpl` exactly:
- `@Service @RequiredArgsConstructor`, field `private final EmployeeWorkUnitRepository employeeWorkUnitRepository;`
- No class-level `@Transactional`. Reads: `@Transactional(readOnly = true)`. Writes: `@Transactional`.
- `getAll()` → `findAll()` mapped with `EmployeeWorkUnitMapper::employeeWorkUnitDTO`.
- `getById(id)` → `findById(id)` helper, mapped.
- `create` → `validateUnique(name.trim(), resolveSymbol(symbol), null)`, save mapper entity, return DTO.
- `update` → load via helper, `validateUnique(..., id)`, apply mapper, save, return DTO.
- `delete` → `employeeWorkUnitRepository.delete(findById(id))`, return `"Employee work unit deleted successfully"`.
- Private `findById(UUID id)` throws
  `new DataNotFoundException("No such employee work unit found with this id")`.
- Private `validateUnique(String name, String symbol, UUID id)` — when `id == null` use `existsByX`, otherwise
  `existsByXAndIdNot`; throw `DataAlreadyExistsException("Employee work unit already exists with this name")` /
  `"... with this symbol"`.
- Exceptions come from `com.codymitra.shared_service.exceptionHandler.exceptions`.
- All mapping must go through `EmployeeWorkUnitMapper` — no inline DTO construction.

### `controllers/EmployeeWorkUnitController.java`
Mirror `EmployeeCategoryController`:
- `@RestController @RequestMapping(path = "/employee_work_units") @RequiredArgsConstructor`
- Field: `private final EmployeeWorkUnitService employeeWorkUnitService;`
- Every method returns `ResponseEntity<Map<String, Object>>` built with
  `com.codymitra.shared_service.responseHandler.ResponseHandler.generateResponse(...)`.
- Endpoints:

| Method | Path      | Body                                     | Status  | Message                                         |
|--------|-----------|------------------------------------------|---------|-------------------------------------------------|
| GET    | `/list`   | —                                        | 200     | `dtos.size() + " total employee work units fetched"` |
| GET    | `/{id}`   | —                                        | 200     | `"Employee work unit fetched successfully"`     |
| POST   | `/create` | `@Valid @RequestBody CreateEmployeeWorkUnitRequest` | 201 | `"Employee work unit created successfully"` |
| PUT    | `/{id}`   | `@Valid @RequestBody CreateEmployeeWorkUnitRequest` | 200 | `"Employee work unit updated successfully"` |
| DELETE | `/{id}`   | —                                        | 200     | message returned by the service                 |

### `data/employee_work_unit.json` (seed data)
Rewrite it so its keys match the entity (`unitType`, `name`, `symbol`, `description`, `active`), e.g.:
```json
[
  { "unitType": "SIMPLE", "name": "Hours",  "symbol": "Hr",  "description": "Work measured in hours",  "active": true },
  { "unitType": "SIMPLE", "name": "Days",   "symbol": "Day", "description": "Work measured in days",   "active": true },
  { "unitType": "SIMPLE", "name": "Pieces", "symbol": "Pcs", "description": "Work measured per piece", "active": true }
]
```
Do not add a seeder unless asked.

## 5. Rules

- Do **not** modify `EmployeeWorkUnitEntity`, `BaseEntity`, `UnitTypeEnum`, or any file outside `employee_work_unit/`.
- Match the surrounding code: Lombok `@RequiredArgsConstructor`, records for DTOs, static mapper methods,
  4-space indentation, same comment density (very few comments).
- Remove unused imports.
- Keep package names `com.codymitra.shared_service.modules.employee_work_unit.*`.

## 6. Verify

JDK 25 may not be installed locally; compile with JDK 21:
```bash
JAVA_HOME=$(/usr/libexec/java_home -v 21) mvn -q -pl shared-service -am compile -Dmaven.compiler.release=21
```
The build must succeed with zero errors. Then grep the module to confirm nothing references
`workUnitType`, `WorkUnitTypeEnum`, `getCode`, `setCode`, `parentId`, or `builder()`:
```bash
grep -rnE 'workUnitType|WorkUnitTypeEnum|getCode|setCode|parentId|builder\(\)' \
  shared-service/src/main/java/com/codymitra/shared_service/modules/employee_work_unit
```
Expected: no output.

Finish by listing each file you changed or deleted with a one-line summary.
