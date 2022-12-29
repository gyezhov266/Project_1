#TELLS DOCKER WHAT ENVIRONMENT TO BUILD OUR APPLICATION ON TOP
FROM openjdk:11-jdk-slim
#FROM maven:3-eclipse-temurin-11-alpine

# Can be used to execute commands before ENTRYPOINT commands
#RUN "mvn package"

# Tells Docker what Files/Directories to save into the container and allows to also rename them
# Left Side is file/directory we want to copy
# Right side is the desired name for that copied file/directory
COPY target/project1-0.0.1-SNAPSHOT.jar project1.jar

RUN 

# Tells Docker what command to run to start up the application
ENTRYPOINT ["java","-jar","project1.jar"]