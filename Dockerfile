FROM gradle:jdk21 AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN ./gradlew -b ./infrastructure/build.gradle.kts build -x test

FROM eclipse-temurin:21-jre-alpine

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/infrastructure/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]