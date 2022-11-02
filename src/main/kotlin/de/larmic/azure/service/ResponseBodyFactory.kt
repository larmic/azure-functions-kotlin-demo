package de.larmic.azure.service

class ResponseBodyFactory {

    fun createResponseBody(name: String?) = "Hello ${name ?: "azure functions"}!"

}