# distance-calculator
Distance calculator between two point on Earth using Haversine calculation with SpringBoot.
# What does it contain?
Project contains db model, rest controller, service, respository, test classes, exceptions.
Postman collection is created to use this API (distance-calculator.postman_collection.json)
# Database
In-memory H2 database is implemented.
After application startups, all data will be inserted from ukpostcodesmysql.sql.
# How to build?
You can build application with maven and jdk-17.
mvn clean install wrapper:wrapper
#Before running
You have to unzip src\main\resources\ukpostcodesmysql.zip before application runs.
# How to run?
You can run application as a springboot application.
# Test
Sample tests are implemented for the methods using mockito and junit.
