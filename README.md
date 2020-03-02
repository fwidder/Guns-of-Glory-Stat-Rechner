# Stat-Rechner Guns of Glory

Ein kleiner Stat-Rechner für das Mobilegame [Guns of Glory](https://play.google.com/store/apps/details?id=com.diandian.gog)!

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
git clone https://github.com/fwidder/Guns-of-Glory-Stat-Rechner.git
```

Then go to the cloned Folder and install the Dependencies with Maven:

```shell script
cd Guns-of-Glory-Stat-Rechner
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

# Needs to be filled from here on!

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
