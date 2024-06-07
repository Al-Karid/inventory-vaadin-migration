# Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
ENV SPRING_ACTIVE_PROFILE=prod
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Pproduction

# Deploy stage
FROM eclipse-temurin:21-jre
COPY --from=build /build/target/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "app.jar"]