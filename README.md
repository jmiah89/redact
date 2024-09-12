## Setup

### Install the JDK

To setup the jdk please install jdk 17, easiest way to do this is follow the guide at the adoptium website https://adoptium.net/en-GB/installation/

On MacOS

`brew install --cask temurin@17`

On Linux

Using apt-get `apt-get install temurin-17-jdk`

Using yum `yum install temurin-17-jdk`

Please make sure your JAVA_HOME points to the binaries for this JDK if the installation process above hasn't done so.

## Run and test the app

### Build the app

Navigate to the root of the app in a command a line and build the app using the gradle wrapper `./gradlew clean build`

###  Run the unit tests

To run unit tests run `./gradlew clean test`

### Run the app

To run the app run `./gradlew clean build bootRun`

The app will run on port 8080 by default, to change this please go into application yaml config from the root of the project to `./src/main/java/resources/application.yaml` and change the line containing the port config  `port: 8080` to the desired port.

### How to call the app

The webservice contains one endpoint `/redact` this can be called with a `POST` request with a string body or a `GET` request.

To call the app you can use the postman collection provided found at root of the project `./HHA Recruitment Test Project.postman_collection.json`

### Redacted Word Configuration
To configure which words get redacted by this service, please go into application yaml config from the root of the project to `./src/main/java/resources/application.yaml`, the yaml property that begins with `redact` will have a sub child called `words` which is a list of words that can be added to.

### Logs
The logging configuration for the logs can be found in the logback configuration file `./src/main/java/resources/logback-spring.xml`

By default the log files are stored at the root of the project under the `./log` folder in the `hha.log` file, by default the file only stores logs from a specific package to keep it easier to read and away from spring logging noise. 

To change the log directory change the property value to your chosen directory in the following property `<property name="LOG_DIR" value="./logs" />` in the logback configuration

## Time Spent: 3 hours