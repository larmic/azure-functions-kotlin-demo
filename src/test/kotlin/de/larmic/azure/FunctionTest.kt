package de.larmic.azure

import com.microsoft.azure.functions.*
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.Optional
import java.util.logging.Logger

class FunctionTest {

    private val azureFunction = Function()

    @Test
    internal fun `call hello endpoint without query param`() {
        val request = mockk<HttpRequestMessage<Optional<String>>>()
        val context = mockk<ExecutionContext>()

        every { context.logger } returns Logger.getGlobal()
        every { request.httpMethod } returns HttpMethod.GET
        every { request.queryParameters } returns emptyMap()
        every { request.createResponseBuilder(any()) } answers {
            val httpStatus = firstArg<HttpStatus>()
            HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(httpStatus)
        }

        val response = azureFunction.run(request, context)

        assertThat(response.status).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("Hello azure functions!")
    }

    @Test
    internal fun `call hello endpoint with query param`() {
        val request = mockk<HttpRequestMessage<Optional<String>>>()
        val context = mockk<ExecutionContext>()

        every { context.logger } returns Logger.getGlobal()
        every { request.httpMethod } returns HttpMethod.GET
        every { request.queryParameters } returns mapOf("name" to "larmic")
        every { request.createResponseBuilder(any()) } answers {
            val httpStatus = firstArg<HttpStatus>()
            HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(httpStatus)
        }

        val response = azureFunction.run(request, context)

        assertThat(response.status).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("Hello larmic!")
    }
}
