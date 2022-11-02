package de.larmic.azure

import com.microsoft.azure.functions.*
import com.microsoft.azure.functions.annotation.*
import de.larmic.azure.configuration.InitKoinDependencyInjection
import de.larmic.azure.service.HelloController
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class HelloAzureFunction : KoinComponent {

    // start koin dependency injection
    private val initDI = InitKoinDependencyInjection

    private val helloController : HelloController by inject()

    @FunctionName("hello")
    fun run(
        @HttpTrigger(
            name = "req",
            methods = [HttpMethod.GET],
            authLevel = AuthorizationLevel.ANONYMOUS
        ) request: HttpRequestMessage<Optional<String>>,
        context: ExecutionContext
    ): HttpResponseMessage {
        return helloController.getHello(request, context)
    }

}