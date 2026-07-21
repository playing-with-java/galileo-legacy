# AGENTS.md

## Tech Stack
- Java 8
- Spring Framework 5.3.x
- Spring Web MVC
- Spring Data JPA + Hibernate 5.4
- H2 Database in-memory
- Maven
- JUnit 4 + Mockito

## Commands
Use these commands exactly as shown:
- Run locally: `mvn jetty:run`
- Build package: `mvn clean package`
- Run tests: `mvn test`
- Checkstyle: `mvn checkstyle:check`
- Full verification: `mvn clean verify`

## Architecture & conventions
- Keep the project in a clear 3-layer structure: Controller -> Service -> Repository.
- Controllers may only depend on services, never on repositories directly.
- Prefer constructor injection.
- Keep DTO mapping logic in dedicated mapper classes when introduced.
- Preserve the current package structure under `com.example.galileo_legacy.feature`.
- Keep changes small and readable.

## Project-specific notes
- The application exposes REST endpoints under `/api/products` and `/api/users`.
- The root endpoint `/` returns a simple HTML page.
- Persistence is configured in [src/main/java/com/example/galileo_legacy/config/AppConfig.java](src/main/java/com/example/galileo_legacy/config/AppConfig.java) with an in-memory H2 datasource.
- Tests live under [src/test/java](src/test/java) and should be updated whenever business logic or endpoints change.

## Workflow rules
- Before finishing a change, run `mvn test`.
- If you modify Java classes, also run `mvn checkstyle:check` or `mvn clean verify`.
- Keep documentation updated in [README.md](README.md) when behavior, endpoints, or commands change.
- Prefer existing patterns in the project rather than introducing new frameworks unless necessary.