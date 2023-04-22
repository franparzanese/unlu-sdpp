# ISO
FROM eclipse-temurin:19-jdk-alpine

COPY . /usr/src/
WORKDIR /usr/src/
RUN apk update && \
    apk add maven
    
RUN mvn clean package

COPY target/demo-0.0.1-SNAPSHOT.jar /usr/app.jar
WORKDIR /usr/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]