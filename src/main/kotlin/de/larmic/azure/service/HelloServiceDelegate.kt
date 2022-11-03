package de.larmic.azure.service

// a simple delegate to demonstrate constructor injection by KOIN
class HelloServiceDelegate(private val helloService: HelloService) {

    infix fun sayHelloTo(name: String?) = helloService.sayHelloTo(name)

}