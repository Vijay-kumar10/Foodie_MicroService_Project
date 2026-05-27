# Sabse choti aur lightweight Java 21 image (Alpine based JRE)
FROM eclipse-temurin:21-jre-alpine

# MAINTAINER ki jagah LABEL (Docker ka naya standard)
LABEL authors="Vijay Kumar"

# Copy our jar file to container
COPY target/Docker-Image-Deployment-0.0.1-SNAPSHOT.jar app.jar

# Run our application
ENTRYPOINT ["java","-jar","app.jar"]