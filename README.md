# Grocery Wholesaler

*A Spring Java application with Rest web services which allows data management and retrieval of produce for a grocery wholesaler.*


----------
**Prerequisites**

 - Docker & Docker-compose
 - Maven

**Build instructions**

    mvn install dockerfile:build

**Deployment instructions**

    docker-compose up
**Application Information**
This application has Swagger which is an API description format for REST APIs. Once deployed, the Swagger UI can be accessed at : http://localhost:8080/swagger-ui.html 

**Un-deploy**

    # will undeploy and data will be persisted
    docker-compose down

[GitHub Repository](https://github.com/warbie118/Grocery-Wholesaler)

