FROM openjdk:17-jdk-slim

WORKDIR /dockerAPI

COPY target/DockerAPI-0.0.1-SNAPSHOT.jar DockerAPI.jar

ENTRYPOINT ["java", "-jar", "DockerAPI.jar"]