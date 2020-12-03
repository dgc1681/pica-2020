# Running stage
FROM openjdk:11.0.5-jdk-slim-buster
VOLUME /tmp
COPY Pasarela-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]