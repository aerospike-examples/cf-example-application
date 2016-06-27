## Cloud Foundry Example Application

This is an example Spring Boot web application that uses services injected by Cloud Foundry in order to receive the parameters (host, port, namespace, set) used to store/retrieve information in an Aerospike database.

The application is looking for two services to be injected:

1. aerospike-cache
2. aerospike-session

In order to utilize this example application, you will need access to a Cloud Foundry environment which has the [Aerospike Service Broker](https://github.com/aerospike/cf-aerospike-service-broker.git) installed. You should also have the [Cloud Foundry CLI](https://github.com/cloudfoundry/cli.git) installed.

### Build

To build the Spring Boot application, run ```gradle build``` from the root directory of this project. That will create the Spring Boot jar file in the ```build/libs``` directory.

### Deploy/Configure

In order to deploy the application using the Cloud Foundry CLI:
1. Follow the instructions on the [Cloud Foundry CLI](https://github.com/cloudfoundry/cli.git) page to setup the CLI
2. CD to the build/libs directory and run ```cf push myapp```

To bind the application to the services:
1. Use ```cf services``` to ensure the ```aerospike-cache``` and ```aerospike-session``` services have already been created. If not, use ```cf create-service``` to create them.
2. Bind the application to the services using ```cf bind-service myapp aerospike-cache``` and ```cf bind-service myapp aerospike-session```.
3. Use ```cf restage myapp``` in order to restage the app and pick up the service credentials

