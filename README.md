# Stat-Rechner für Dome

Ein kleiner Stat-Rechner für Dome's Mobilegame!

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You will need Java (min Version 11) Maven and a Git-Client to get this Project running!

```
# For Ubuntu try This:
sudo apt update
sudo apt install --yes default-jdk
sudo apt install --yes git
sudo apt install --yes maven
```

### Installing

A step by step series of examples that tell you how to get a development env running

First clone the Repository:

```shell script
git clone https://github.com/fwidder/DomeGameCalc.git
```

Then go to the cloned Folder and install the Dependencies with Maven:

```shell script
cd DomeGameCal
mvn clean install
```

To run the Project locally run:
```shell script
mvn spring-boot:run
```
 The following URL's will be available (by default the App will run on Port 8080) :

| URL                                       | Content                       |
| ----------------------------------------- | ----------------------------- |
| [/](http://localhost:8080/)               | Start Page                    |
| [/swagger](http://localhost:8080/swagger) | Documentation of the REST-API |
