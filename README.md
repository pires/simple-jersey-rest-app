simple-jersey-rest-app
====================

[![Build Status](https://drone.io/github.com/pires/simple-jersey-rest-app/status.png)](https://drone.io/github.com/pires/simple-jersey-rest-app/latest)

A simple Java EE 7 (JAX-RS 2.0) application that features:
* GSON as JSON provider
* A working implementation of _ApplicationEventListener_
* Example of JAX-RS 2.0 client API usage (look for it in the integration tests)

## Prerequisites ##
- JDK 7
- Maven 3.0.3 or newer
- Glassfish 4.0 or newer (**only** if you want to deploy the WAR file)

## Test ##
```
mvn clean test
```

## Generate WAR ##
```
mvn clean package
```
