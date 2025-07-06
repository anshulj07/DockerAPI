FROM openjdk:17-jdk-slim

WORKDIR /dockerFile

COPY target/DockerAPI-0.0.1-SNAPSHOT.jar DockerAPI.jar

ENTRYPOINT ["java", "-jar", "DockerAPI.jar"]