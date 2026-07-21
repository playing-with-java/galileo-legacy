# Galileo Legacy

Aplicación web Java basada en Spring MVC y Spring Data JPA para exponer una API REST simple para gestionar usuarios y productos.

## Stack técnico

- Java 8
- Spring Framework 5.3.x
- Spring Web MVC
- Spring Data JPA
- Hibernate 5.4
- H2 Database en memoria
- Lombok
- Maven
- JUnit 4 + Mockito para pruebas unitarias
- Checkstyle

## Estructura principal

- Configuración Spring: [src/main/java/com/example/galileo_legacy/config](src/main/java/com/example/galileo_legacy/config)
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

## Ejecución local

Desde la raíz del proyecto:

```bash
mvn clean package
```

Y luego, si se desea ejecutar con el plugin de Jetty:

```bash
mvn jetty:run
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

La configuración JPA y datasource se define en [src/main/java/com/example/galileo_legacy/config/AppConfig.java](src/main/java/com/example/galileo_legacy/config/AppConfig.java).

## Pruebas

Ejecutar todas las pruebas:

```bash
mvn test
```

El proyecto incluye pruebas unitarias para mappers, services y controllers con JUnit 4 y Mockito.

## Verificación de estilo

```bash
mvn checkstyle:check
```

O bien:

```bash
mvn clean verify
```
