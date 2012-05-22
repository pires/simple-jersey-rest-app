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

Then, deploy the resulting _target/jersey_rest-0.1.war_ to your Glassfish domain instance with _jersey_rest_ as application context-root.

## Test ##

Point your browser to:
* Retrieve all ISPs in JSON:  _http://localhost:8080/jersey_rest/rest/isp/list_
* Retrieve single ISP in JSON: _http://localhost:8080/jersey_rest/rest/isp/1_
* Upload ISP in JSON: execute _./test_post_isp_json.sh_
