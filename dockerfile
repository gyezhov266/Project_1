# Setting an alias for the image we can reference its files/directories in later steps
FROM maven:3-adoptopenjdk-11 as builder
COPY ./src src
COPY ./pom.xml pom.xml
RUN mvn package

FROM openjdk:11-jdk-slim
COPY --from=builder target/project1-0.0.1-SNAPSHOT.jar project1.jar
ENTRYPOINT ["java","-jar","project1.jar"]