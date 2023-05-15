# Build
FROM gradle:6.8.3-jdk11 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Run
FROM openjdk:11-jre
EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/golden-awards-api.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","/app/golden-awards-api.jar"]