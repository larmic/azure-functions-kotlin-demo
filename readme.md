# Azure functions demo using Kotlin and Maven

[![CI with Maven](https://github.com/larmic/azure-functions-kotlin-demo/actions/workflows/maven.yml/badge.svg)](https://github.com/larmic/azure-functions-kotlin-demo/actions/workflows/maven.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This demo is based on [microsoft documentation](https://learn.microsoft.com/en-us/azure/azure-functions/functions-create-first-kotlin-maven?tabs=bash).

This demo is still in progress!

## Technologies

:green_circle: Kotlin 1.7.x with Java 17
:green_circle: Maven
:green_circle: JUnit 5.x
:green_circle: [Mockk](https://mockk.io/) test mocking framework
:green_circle: [Kotest Assertions](https://kotest.io/docs/assertions/assertions.html) test assertion framework
:red_circle: [Koin](https://insert-koin.io/) dependency injection framework
:red_circle: "Real" integration tests starting local azure functions container

## Prerequisites
* Java 17
* Maven >= 3.2
* [Azure CLI](https://learn.microsoft.com/en-us/cli/azure/)
* [Azure Functions Core Tools v4](https://learn.microsoft.com/en-us/azure/azure-functions/functions-run-local?tabs=v4%2Cmacos%2Ccsharp%2Cportal%2Cbash)

## Clone repository and build project

```sh
$ git clone https://github.com/larmic/azure-functions-kotlin-demo

$ mvn clean test                    # Compiles codes and runs unit tests
$ mvn clean package                 # Like test but also build local artifact
```

### Run functions locally 

```sh
# start local functions container using azure-functions-maven-plugin 
$ mvn azure-functions:run 

# http call
$ curl http://localhost:7071/api/hello?name=larmic
```