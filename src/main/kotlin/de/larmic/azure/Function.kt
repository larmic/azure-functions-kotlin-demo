package de.larmic.azure

import java.util.*
import com.microsoft.azure.functions.*
import com.microsoft.azure.functions.annotation.*

class Function {

    @FunctionName("hello")
    fun run(
        @HttpTrigger(name = "req",
            methods = [HttpMethod.GET],
            authLevel = AuthorizationLevel.ANONYMOUS
        ) request: HttpRequestMessage<Optional<String>>,
        context: ExecutionContext
    ): HttpResponseMessage {

        context.logger.info("HTTP trigger processed a ${request.httpMethod.name} request.")

        val query = request.queryParameters["name"]
        val name = request.body.orElse(query)

        name?.let {
            return request
                .createResponseBuilder(HttpStatus.OK)
                .body("Hi, $name!")
                .build()
        }

        return request
            .createResponseBuilder(HttpStatus.BAD_REQUEST)
            .body("Please pass a name on the query string or in the request body")
            .build()
    }

}
