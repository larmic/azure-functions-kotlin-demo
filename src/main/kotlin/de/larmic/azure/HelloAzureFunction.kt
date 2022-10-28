package de.larmic.azure

import java.util.*
import com.microsoft.azure.functions.*
import com.microsoft.azure.functions.annotation.*
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.core.component.inject

class HelloAzureFunction : KoinComponent {

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

object InitKoinDependencyInjection {
    init {
        startKoin {
            modules(module {
                singleOf(::HelloController)
                singleOf(::ResponseBodyFactory)
            })
        }
    }
}