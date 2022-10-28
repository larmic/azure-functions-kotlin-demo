package de.larmic.azure

class ResponseBodyFactory {

    fun createResponseBody(name: String?) = "Hello ${name ?: "azure functions"}!"

}