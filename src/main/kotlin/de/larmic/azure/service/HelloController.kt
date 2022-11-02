package de.larmic.azure.service

import com.microsoft.azure.functions.ExecutionContext
import com.microsoft.azure.functions.HttpRequestMessage
import com.microsoft.azure.functions.HttpResponseMessage
import com.microsoft.azure.functions.HttpStatus
import java.util.*

class HelloController(private val responseBodyFactory : ResponseBodyFactory) {

    fun getHello(request: HttpRequestMessage<Optional<String>>, context: ExecutionContext) : HttpResponseMessage {
        context.logger.info("HTTP trigger processed a ${request.httpMethod.name} request.")

        val body = responseBodyFactory.createResponseBody(request.queryParameters["name"])

        return request
            .createResponseBuilder(HttpStatus.OK)
            .body(body)
            .build()
    }

}