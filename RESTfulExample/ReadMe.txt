mvn clean install -DskipTests
deploy the created file RESTfulExample-1.0-SNAPSHOT.war

1. Resource Path:
The @Path(“/users”) annotation at the class level defines the base path for the resource.

2. Endpoint Path:
The @Path(“/{userId}”) annotation at the method level specifies that this method will handle GET requests to paths like
/users/123, where 123 is a dynamic part of the URL.

3. PathParam Annotation:
The @PathParam(“userId”) annotation binds the {userId} path parameter to the userId method parameter.
This means that if a request comes in for /users/123, the value 123 will be passed to the userId parameter in the getUserById method.