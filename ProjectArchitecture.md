# Project Architecture

## Module structure convention

Every feature lives in `shared-service/src/main/java/com/codymitra/shared_service/modules/<feature_name>/` using package-by-feature layout (not package-by-layer). The standard sub-packages are:

- `controllers/` — REST controllers, `@RestController` + `@RequestMapping(path = "/<feature_plural>")` (snake_case plural, e.g. `/voucher_types`)
- `dtos/` — request/response DTOs
- `entities/` — JPA entities, named `<Feature>Entity.java`
- `mappers/` — `final` mapper classes
- `repositories/` — Spring Data `JpaRepository<Entity, Long>` interfaces
- `services/` — service interfaces
- `services/impl/` — `@Service` implementations named `<Feature>ServiceImpl`

Reference implementation: `modules/voucher/` (and `modules/voucher_type/`).
