# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
RUN ls -alh /app/target
# Stage 2: Runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/springboot-neo4j*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
