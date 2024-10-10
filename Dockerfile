# For Java 17
FROM eclipse-temurin:17-jdk-alpine

# cd /opt/dockerapp
WORKDIR /opt/dockerapp

# Refer to Maven build -> finalName
ARG JAR_FILE=target/jwt-1.0.0.jar

# cp target/spring-jwt-0.0.1-SNAPSHOT.jar /opt/dockerapp/dockerapp.jar
COPY ${JAR_FILE} dockerapp.jar

# java -jar /opt/dockerapp/dockerapp.jar
ENTRYPOINT ["java","-jar","dockerapp.jar"]