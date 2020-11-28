# Building stage
#FROM maven:3.6.2-jdk-11-slim AS builder
#WORKDIR /build
#COPY pom.xml .
#RUN mvn dependency:go-offline
#COPY ./src ./src
#RUN mvn clean package

#RUN mvn clean
#RUN mvn compile
#RUN mvn package

# Running stage
FROM openjdk:11.0.5-jdk-slim-buster
VOLUME /tmp
COPY Orden-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]