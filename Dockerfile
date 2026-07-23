# ============================
# Stage 1 - Build
# ============================
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /workspace

COPY pom.xml .
COPY config ./config
COPY src ./src

RUN mvn clean package -DskipTests

# ============================
# Stage 2 - Runtime
# ============================
FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="equipo-dev"

WORKDIR /app

COPY --from=builder /workspace/target/galileo-legacy.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]