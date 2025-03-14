//A 1. Basic CRUD Spring 3.5 project 2. Not mapped to a DB 3. With Swagger support
mvn clean install -DskipTests

https://stackoverflow.com/questions/15598210/the-import-javax-persistence-cannot-be-resolved
The javax.persistence package was moved to a newly named dependency (jakarta.persistence.

Integrate Swagger UI
https://www.baeldung.com/spring-rest-openapi-documentation

Spring Boot 3.x requires to use version 2 of springdoc-openapi:
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.4</version>
</dependency>

http://localhost:8080/swagger-ui/index.html
https://stackoverflow.com/questions/34253779/tomcat-server-error-port-8080-already-in-use