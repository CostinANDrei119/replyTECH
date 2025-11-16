# Multi-stage build for Spring Boot application
FROM maven:3.8.8-eclipse-temurin-17 AS builder
WORKDIR /workspace

# copy only maven metadata first for better caching
COPY pom.xml mvnw ./
COPY .mvn .mvn

# copy source
COPY src ./src

# package application (skip tests for faster builds)
RUN mvn -B -DskipTests package

# Runtime image
FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=target/replytech-0.0.1-SNAPSHOT.jar
COPY --from=builder /workspace/${JAR_FILE} /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
