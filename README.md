## Cloud Foundry Example Application

This is an example Spring Boot web application that uses services injected by Cloud Foundry in order to receive the parameters (host, port, namespace, set) used to store/retrieve information in an Aerospike database.

The application is looking for two services to be injected:

1. aerospike-cache
2. aerospike-session

In order to utilize this example application, you will need access to a Cloud Foundry environment which has the [Aerospike Service Broker](https://github.com/aerospike/cf-aerospike-service-broker.git) installed. You should also have the [Cloud Foundry CLI](https://github.com/cloudfoundry/cli.git) installed.

### Push Application to Cloud Foundry

#### Option 1: Use Pre-Built Spring Boot Jar File

From the root directory of this project run ```cf push myapp -p ./cf-example-app-X.X.X-SNAPSHOT.jar``` (replace X's with actual version number)

#### Option 2: Build the Spring Boot Jar File

To build the Spring Boot application, run ```gradle build``` from the root directory of this project. That will create the Spring Boot jar file in the ```build/libs``` directory.
CD to the build/libs directory and run ```cf push myapp -p ./cf-example-app-X.X.X-SNAPSHOT.jar``` (replace X's with actual version number)

### Configure

To bind the application to the services:

1. Use ```cf services``` to ensure the ```aerospike-cache``` and ```aerospike-session``` services have already been created. If not, use ```cf create-service``` to create them.
2. Bind the application to the services using ```cf bind-service myapp aerospike-cache``` and ```cf bind-service myapp aerospike-session```.
3. Use ```cf restage myapp``` in order to restage the app and pick up the service credentials

