//C 1. Kafka DockerCompose Spring 3.4 project 2. With Swagger support 3. Kafka UI support

https://medium.com/@afdulrohmat03/building-a-simple-kafka-producer-consumer-in-spring-boot-with-docker-compose-66c082404a09

1.add dependencies to pom.xml file
Spring Web
Lombok
Spring for Apache Kafka

2. Add docker-compose.yaml  file
3. Add Docker file
4. Ensure following structure
my-docker-project/
   |-- src/
   |   |-- main/
   |       |-- java/ [source root]
   |-- docker-compose.yml
   |-- Dockerfile

5. mvn package -DskipTests
6. docker-compose up //docker-compose down   docker-compose up  -d

http://localhost:8081/swagger-ui/index.html