# Codymitra Account ERP API

Codymitra is a Spring Boot–based backend for an accounting and inventory ERP product. It models the core building blocks of a double-entry accounting system — ledgers, vouchers, journals, financial years — alongside inventory/stock management (stock items, groups, categories, units, storage locations) and organizational structure (legal entities, internal organizations, teams, operation units).

The backend is split into independently deployable microservices, all on **Java 25**.

## Services

| Service | Purpose | Status |
|---|---|---|
| `codymitra-service` | Main runtime. Boots on port `8060` under context path `/api`, backed by PostgreSQL. Hosts the full domain surface (accounting, vouchers, journals, inventory) via `shared-service`. | Runnable end-to-end |
| `auth-service` | Authentication service — JWT issuing/validation, Spring Security config, request filters. | Active |
| `shared-service` | Common library of domain entities, DTOs, mappers, services, and controllers, organized **by feature** under `modules/<feature>`. Reused by `codymitra-service`. | Active — most domain code lives here |
| `organization-service` | Organizational structure: legal entities, internal organizations, operation units, teams, and org hierarchy. | Active |
| `journal-service` | Standalone service for journal processing. | Skeleton, not yet wired into the reactor |
| `voucher-service` | Standalone service for voucher processing. | Skeleton, not yet wired into the reactor |

### Reactor structure

The root `pom.xml` is a parent/reactor POM (`packaging=pom`, Spring Boot parent `3.5.16`). Its `<modules>` list includes **codymitra-service, auth-service, shared-service, organization-service** — these four share the root parent and dependency set (Spring Web, Spring Data JPA, PostgreSQL driver, Spring Security, validation, commons-lang3, Lombok).

`journal-service` and `voucher-service` are **not** part of the root reactor. Each declares its own `spring-boot-starter-parent` (version `4.1.0`, a different major version than the root's `3.5.16`) and must be built/run independently from its own directory.

## Domain modules (`shared-service`)

Entities live under `modules/<feature>/entities`, following a package-by-feature layout rather than package-by-layer. Current modules include, among others:

- **Accounting**: `account_group`, `account_ledger`, `account_nature`, `journal`, `journal_entry`, `journal_reference`, `journal_type`, `voucher`, `voucher_entry`, `voucher_reference`, `voucher_type`, `financial_year`, `company_financial_year`
- **Inventory / stock**: `stock_item`, `stock_group`, `stock_category`, `stock_unit`, `storage_location`, `storage_unit`, `inventory_type`, `costing_methods`, `bom`, `unique_quantity_code`, `tax_rate_master`
- **Parties & master data**: `customer`, `party`, `company`, `address`, `country`, `state`, `language`, `department`, `designation`, `document`, `user`

## Tech stack

- Java 25
- Spring Boot 3.5.16 (`codymitra-service`, `auth-service`, `shared-service`, `organization-service`); Spring Boot 4.1.0 (`journal-service`, `voucher-service`, standalone)
- PostgreSQL + Spring Data JPA (`spring.jpa.hibernate.ddl-auto=update` — no migration tool in use)
- Lombok for entity/DTO boilerplate
- JWT-based auth (`auth-service`)

## Getting started

### Prerequisites

- JDK 25
- A local PostgreSQL instance with a database named `account_erp` (see `codymitra-service/src/main/resources/application.properties` for connection details)

### Build

Reactor build (`codymitra-service`, `auth-service`, `shared-service`, `organization-service`), from the repo root:

```bash
mvn clean install
```

Single reactor module, e.g. `codymitra-service`:

```bash
mvn -pl codymitra-service -am clean install
```

### Run

Main app, from `codymitra-service/`:

```bash
./mvnw spring-boot:run
```

Standalone services must be built/run from their own directory:

```bash
cd journal-service && ./mvnw spring-boot:run
cd voucher-service && ./mvnw spring-boot:run
```

### Test

```bash
mvn test                 # all reactor modules, from repo root
./mvnw test               # single module, from that module's directory
./mvnw test -Dtest=ClassName   # single test class
```

## More detail

See `CLAUDE.md` for architecture conventions, known inconsistencies, and detailed dev guidance for this repo.
