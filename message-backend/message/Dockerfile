FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY message/pom.xml /app/message/pom.xml
COPY message/src app/message/src
RUN mvn -f /app/message/pom.xml clean install -DskipTests
RUN mvn -f /app/message/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/message/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]