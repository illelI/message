FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY user/pom.xml /app/user/pom.xml
COPY user/src app/user/src
RUN mvn -f /app/user/pom.xml clean install -DskipTests
RUN mvn -f /app/user/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/user/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]