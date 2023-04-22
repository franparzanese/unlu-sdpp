# ISO
FROM eclipse-temurin:19-jdk-alpine

COPY target/demo-0.0.1-SNAPSHOT.jar /usr/src/app.jar
WORKDIR /usr/src/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]