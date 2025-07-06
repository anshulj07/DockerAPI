FROM openjdk:17-jdk-slim

WORKDIR /dockerFile

COPY wait-for-it.sh wait-for-it.sh
COPY target/DockerAPI-0.0.1-SNAPSHOT.jar DockerAPI.jar
RUN chmod +x wait-for-it.sh

ENTRYPOINT ["./wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "DockerAPI.jar"]