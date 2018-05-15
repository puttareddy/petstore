# Simple Spring Boot Server

## Deploy to Minikube
Please follow [minikube guide] to deploy this service to Minikube cluster

[minikube guide]: <minikube.md>

## Local Deployment

Build the Client first. Then run

    mvn clean package
    java -jar target/pet-store-*-SNAPSHOT.jar

## Development server

Run `mvn spring-boot:run` for a dev server.

## Swagger Specification
Navigate to Swagger UI [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Running unit tests

Run `mvn clean test` to execute the JUnit tests.

## Testing API

    curl -v -X POST -d '{"name":"Tommy"}' -H "content-type:application/json" http://localhost:8080/api/pet
    curl -v -X GET  http://localhost:8080/api/pet/21
    curl -v -X PUT -d '{"id":21,"name":"Tommy 2"}' -H "content-type:application/json" http://localhost:8080/api/pet
    curl -v -X DELETE  http://localhost:8080/api/pet/21
