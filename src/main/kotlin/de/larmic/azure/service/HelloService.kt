package de.larmic.azure.service

class HelloService {

    infix fun sayHelloTo(name: String?) = "Hello ${name ?: "azure functions"}!"

}