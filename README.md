## README.md
This is a Java Spring boot with Mongo sample application
Need MongoDB to run this. Install MongoDB. Look more for application.yml

## Local Build and deployment
- Clone project
- Import to Eclipse as a Maven project
- Build: mvn clean package
- Run: mvn spring-boot:run

## Swagger Urls
http://localhost:9001/swagger-ui.html

## Application urls
###GET:
curl -X GET http://localhost:9001/api/v1/person/all
curl -X GET http://localhost:9001/api/v1/person/{firstName}

###PUT:
curl -d '{"id":"1", "firstName":"FirstName1", "lastName":"LastName1", "age":25}' -H "Content-Type: application/json" -X POST http://localhost:9001/api/v1/person

###POST:
curl -d '{"id":"1", "firstName":"FirstName1", "lastName":"LastName1", "age":25}' -H "Content-Type: application/json" -X POST http://localhost:9001/api/v1/person

###DELETE
curl -X DELETE http://localhost:9001/api/v1/person
