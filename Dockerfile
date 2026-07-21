# ============================
# Stage 1 - Build
# ============================
FROM maven:3.9.11-eclipse-temurin-8 AS builder

WORKDIR /workspace

COPY pom.xml .
COPY config ./config
COPY src ./src

RUN mvn clean package -DskipTests

# ============================
# Stage 2 - Runtime
# ============================
FROM tomcat:9.0-jdk8-temurin

LABEL maintainer="equipo-dev"

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=builder \
    /workspace/target/*.war \
    /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]