package de.larmic.azure

import com.microsoft.azure.functions.*
import com.microsoft.azure.functions.annotation.*
import de.larmic.azure.configuration.InitKoinDependencyInjection
import de.larmic.azure.service.HelloServiceDelegate
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class HelloAzureFunction : KoinComponent {

    // start koin dependency injection
    private val initDI = InitKoinDependencyInjection

    private val helloServiceDelegate : HelloServiceDelegate by inject()

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

        val name = request.queryParameters["name"]
        val response =  helloServiceDelegate.sayHello(name)

        return request
            .createResponseBuilder(HttpStatus.OK)
            .body(response)
            .build()
    }

}