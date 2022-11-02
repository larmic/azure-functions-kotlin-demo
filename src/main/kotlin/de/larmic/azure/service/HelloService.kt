package de.larmic.azure.service

class HelloService {

    fun sayHello(name: String?) = "Hello ${name ?: "azure functions"}!"

}