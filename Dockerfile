# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -q

COPY src ./src
RUN mvn package -q -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/banking-1.0-SNAPSHOT.jar app.jar
COPY resources/bank.db resources/bank.db

ENTRYPOINT ["java", "-jar", "app.jar"]