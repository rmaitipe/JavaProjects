//C 1. CRUD DockerCompose Spring 3.4 project 2. Postgres DB support 3. With Swagger support

Some of the resources referenced in this project
https://medium.com/@saygiligozde/using-docker-compose-with-spring-boot-and-postgresql-235031106f9f
https://medium.com/@AlexanderObregon/spring-hateoas-building-hypermedia-driven-restful-apis-70aa02133c2f
https://www.baeldung.com/lombok-builder Builder pattern

1.add dependencies to pom.xml file
<dependency>
<groupId>org.postgresql</groupId>
<artifactId>postgresql</artifactId>
<scope>runtime</scope>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>

2. Add docker-compose.yaml  file
3. Add Docker file
4. Ensure following structure
my-docker-project/
   |-- src/
   |   |-- main/
   |        |-- java/ [source root]
   |-- docker-compose.yml
   |-- Dockerfile

5. mvn clean package -DskipTests
6. docker-compose up //docker-compose down
7. http://localhost:8081/customers
   http://localhost:8080/swagger-ui/index.html (Run w/o Docker maps to 8080)

DataInitializer/DataLoader 
The init.sql creates the db and populates with initial data from the csv file.
Similarly either DataInitializer & DataLoader(@PostConstruct) classes can also setup data.

* Observations during data setup
init.csv runs 2 times creating duplicates + missing first record from DataInitializer
When init.sql copy is commented out both DataInitializer & DataLoader worked through Docker
(worked through Docker but not through local-because of entry point?)