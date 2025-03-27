# fintonic-base

## Requirements

For building and running the application you need:

- [JDK 21](https://www.oracle.com/es/java/technologies/downloads/#java21)
## Build the application locally
There is a docker-compose file with all the infrastructure that the service needs. Before the build:
````shell
./docker-compose up -d --wait
````
And then:
```shell
./gralew clean build
```

There are three kind of tests in this service: unit, integration and acceptance.
In order to run each one:

```shell
./gralew unit | integration | acceptance
```


## Running the application locally
There are several ways to run a Spring Boot application:

- execute the `main` method in the `com.fintonic.FintonicBaseApplication.java` class
- Using gradle:
```shell
./gradlew bootRun
```


## Changelog
### Version 0.0.0
* Spring Boot starter service