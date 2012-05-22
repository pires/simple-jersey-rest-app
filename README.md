simple-jersey-rest-app
====================

A simple proof-of-concept of RESTful web service implemented with Jersey and Glassfish.

## Prerequisites ##
- JDK 6
- Maven 3.0.3 or newer
- Glassfish 3.1.1 or newer

## Build WAR and deploy to Glassfish ##

In order to build a WAR package, run the following command:  

_mvn clean package_

Then, deploy the resulting _target/simple-jersey-rest-app-0.1.war_ to your Glassfish domain instance.

## Test ##

Point your browser to _http://localhost:8080/jersey-rest-app-0.1/rest/admin/isp_
