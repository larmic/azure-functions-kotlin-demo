package de.larmic.azure

import java.util.*
import com.microsoft.azure.functions.*
import com.microsoft.azure.functions.annotation.*

class HelloAzureFunction {

    @FunctionName("hello")
    fun run(
        @HttpTrigger(
            name = "req",
            methods = [HttpMethod.GET],
            authLevel = AuthorizationLevel.ANONYMOUS
        ) request: HttpRequestMessage<Optional<String>>,
        context: ExecutionContext
    ): HttpResponseMessage {
        context.logger.info("HTTP trigger processed a ${request.httpMethod.name} request.")

        val name = request.queryParameters["name"] ?: "azure functions"

        return request
            .createResponseBuilder(HttpStatus.OK)
            .body("Hello $name!")
            .build()
    }

}
