# Build stage
#
#FROM maven:3.8.3-openjdk-17 AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package

# Run stage
#
#FROM openjdk:17
#COPY --from=build /home/app/target/DockerBaseString-1.0-SNAPSHOT.jar /usr/local/lib/DockerBaseString-1.0-SNAPSHOT.jar
#EXPOSE 8080
#USER 10014
#ENTRYPOINT ["java","-jar","/usr/local/lib/DockerBaseSpring-1.0-SNAPSHOT.jar"]

FROM openjdk:23
COPY /target/CrudSpringH2Docker-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
USER 10014
ENTRYPOINT ["java","-jar","app.jar"]