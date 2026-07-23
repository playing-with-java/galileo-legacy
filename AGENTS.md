# AGENTS.md

## Tech Stack
- Java 21
- Spring Boot 3.5.x
- Spring Web MVC (via `spring-boot-starter-web`)
- Spring Data JPA + Hibernate 6.x (via `spring-boot-starter-data-jpa`)
- H2 Database in-memory
- Lombok
- springdoc-openapi 2.x (Swagger UI / OpenAPI 3)
- Maven
- JUnit 5 (Jupiter) + Mockito (via `spring-boot-starter-test`)
- Checkstyle

## Commands
Use these commands exactly as shown:
- Run locally: `mvn spring-boot:run`
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
- The application exposes full CRUD REST endpoints under `/api/products` and `/api/users`.
- The root endpoint `/` returns a simple HTML page.
- Persistence is configured in [src/main/resources/application.properties](src/main/resources/application.properties) with an in-memory H2 datasource (`jdbc:h2:mem:galileo`).
- H2 console is available at `/h2-console` when the app is running.
- OpenAPI documentation is served at `/swagger-ui/index.html` and `/v3/api-docs`.
- Virtual threads are enabled (`spring.threads.virtual.enabled=true`).
- Checkstyle rules are defined in [config/checkstyle/checkstyle.xml](config/checkstyle/checkstyle.xml) and run on the `validate` phase.
- Docker support is available via [Dockerfile](Dockerfile) (multi-stage build using `eclipse-temurin:21`) and [docker-compose.yml](docker-compose.yml).
- Tests live under [src/test/java](src/test/java) and should be updated whenever business logic or endpoints change.

## Workflow rules
- Before finishing a change, run `mvn test`.
- If you modify Java classes, also run `mvn checkstyle:check` or `mvn clean verify`.
- Keep documentation updated in [README.md](README.md) when behavior, endpoints, or commands change.
- Prefer existing patterns in the project rather than introducing new frameworks unless necessary.