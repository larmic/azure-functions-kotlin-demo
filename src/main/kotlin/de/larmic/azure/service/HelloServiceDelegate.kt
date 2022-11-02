package de.larmic.azure.service

// a simple delegate to show helloService can be injected by constructor
class HelloServiceDelegate(private val helloService: HelloService) {

    fun sayHello(name: String?) = helloService.sayHello(name)

}