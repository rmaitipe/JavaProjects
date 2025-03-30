# the base image
#FROM amazoncorretto:17
FROM openjdk:23
# the JAR file path
ARG JAR_FILE=target/*.jar

# Copy the JAR file from the build context into the Docker image
COPY ${JAR_FILE} application.jar

CMD apt-get update -y

# Set the default command to run the Java application
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]

#docker run -p 4000:8080 --name spring-restdb-container spring-restdb:1.0