//A 1. Basic CRUD Spring 3.5 project 2. Not mapped to a DB 3. With Swagger support 4. No Authentication
mvn clean install -DskipTests
http://localhost:8080/swagger-ui/index.html

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

https://stackoverflow.com/questions/34253779/tomcat-server-error-port-8080-already-in-use
___________________________________________________________________________________________
@SpringBootApplication SpringApplication.run
@Entity, @Id, @GeneratedValue(strategy = AUTO)
@RestController, @RequestMapping, ResponseEntity, @Valid @RequestBody, @PathVariable
@Repository, extends CrudRepository<?,Long>
@Service, findById,save,delete
extends RuntimeException
@ControllerAdvice, @ExceptionHandler
-------------------------------------------------------------------------------------------
https://blog.jetbrains.com/idea/2024/10/how-to-build-a-crud-rest-api-using-spring-boot/ [JPA Buddy plugin]
https://medium.com/@piyumisudusinghe/error-handling-in-rest-api-4fa7bdbe379a [ResponseEntity manipulation]
https://www.forestadmin.com/blog/an-experts-guide-to-crud-apis-designing-a-robust-one/ [Best Practices]

Spring Data JPA https://www.baeldung.com/spring-data-derived-queries, https://www.baeldung.com/spring-data-sorting
Spring Data’s method derivation, the query is generated from the method name and signature.
All we need to do is include the keyword OrderBy in our method name, along with the property name(s) and direction (Asc or Desc) by which we want to sort.
Spring Data JPA supports find, read, query, count and get. We can also use Distinct, First or Top to remove duplicates or limit our result set.
    List<Passenger> findByOrderBySeatNumberAsc(); List<User> findTop3ByAge();


For a Spring Boot application, an explicit @Transactional declaration is never required. Spring just creates a default one for us per request.
If changing the behaviour in a multi-transaction, we need to create a separate Spring Bean/Service to invoke the method required to run in a separate new transaction
https://reachnikhil14.medium.com/using-transactional-with-spring-boot-rest-endpoints-a04d73b6eb0f