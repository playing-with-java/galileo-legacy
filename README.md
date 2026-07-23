# Galileo Legacy

Aplicación web Java basada en Spring Boot 3.5 y Spring Data JPA para exponer una API REST simple para gestionar usuarios y productos.

## Stack técnico

- Java 21
- Spring Boot 3.5.x
- Spring Web MVC (via spring-boot-starter-web)
- Spring Data JPA (via spring-boot-starter-data-jpa)
- Hibernate 6.x
- H2 Database en memoria
- Lombok
- Maven
- JUnit 5 (Jupiter) + Mockito para pruebas unitarias
- springdoc-openapi 2.x para documentación Swagger
- Checkstyle

## Estructura principal

- Punto de entrada Spring Boot: [src/main/java/com/example/galileo_legacy/GalileoLegacyApplication.java](src/main/java/com/example/galileo_legacy/GalileoLegacyApplication.java)
- Configuración Swagger: [src/main/java/com/example/galileo_legacy/config](src/main/java/com/example/galileo_legacy/config)
- Controladores: [src/main/java/com/example/galileo_legacy/feature](src/main/java/com/example/galileo_legacy/feature)
- Entidades y DTOs: [src/main/java/com/example/galileo_legacy/feature](src/main/java/com/example/galileo_legacy/feature)
- Pruebas: [src/test/java](src/test/java)
- Configuración de Checkstyle: [config/checkstyle/checkstyle.xml](config/checkstyle/checkstyle.xml)

## Endpoints disponibles

### Raíz
- `GET /` → devuelve una respuesta HTML simple indicando que la API está activa.

### Productos
- `GET /api/products`
- `GET /api/products/{id}`
- `POST /api/products`
- `PUT /api/products/{id}`
- `DELETE /api/products/{id}`

### Usuarios
- `GET /api/users`
- `GET /api/users/{id}`
- `POST /api/users`
- `PUT /api/users/{id}`
- `DELETE /api/users/{id}`

## Documentación Swagger

La API incluye documentación OpenAPI 3 generada con springdoc-openapi.

### Acceso a la documentación

Una vez levantada la aplicación, puedes abrir:

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- JSON de la API: http://localhost:8080/v3/api-docs

La documentación incluye los endpoints de productos y usuarios, así como sus parámetros, respuestas y códigos HTTP esperados.

## Ejecución local

Desde la raíz del proyecto:

```bash
mvn clean package
```

Y luego, para ejecutar con el plugin de Spring Boot:

```bash
mvn spring-boot:run
```

La aplicación queda disponible en:
- http://localhost:8080/

## Ejecución con Docker

Se incluye un archivo de Docker Compose para levantar la aplicación:

```bash
docker compose up --build
```

La aplicación queda expuesta en el puerto 8081 del host.

## Configuración

La configuración principal está en [src/main/resources/application.properties](src/main/resources/application.properties).

Valores actuales:
- DDL automático: `create-drop`
- Dialecto Hibernate: `org.hibernate.dialect.H2Dialect`
- SQL mostrado por Hibernate: habilitado
- Consola H2: habilitada en `/h2-console`

La configuración de datasource y JPA se gestiona automáticamente por Spring Boot a partir de las propiedades en `application.properties`.

## Pruebas

Ejecutar todas las pruebas:

```bash
mvn test
```

El proyecto incluye pruebas unitarias para mappers, services y controllers con JUnit 5 y Mockito.

## Verificación de estilo

```bash
mvn checkstyle:check
```

O bien:

```bash
mvn clean verify
```
