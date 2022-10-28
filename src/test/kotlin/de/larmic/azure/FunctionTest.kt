package de.larmic.azure

import com.microsoft.azure.functions.*
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.Optional
import java.util.logging.Logger

class FunctionTest {

    // the azure function to test
    private val azureFunction = Function()

    // request mock with default behavior
    private val requestMock = mockk<HttpRequestMessage<Optional<String>>>() {
        every { httpMethod } returns HttpMethod.GET
        every { createResponseBuilder(any()) } answers {
            firstArg<HttpStatus>().wrapByHttpResponseMessageBuilderMock()
        }
    }

    // execution context mock with default behavior
    private val executionContextMock = mockk<ExecutionContext> {
        every { logger } returns Logger.getGlobal()
    }

    @Test
    internal fun `call hello endpoint without query param`() {
        every { requestMock.queryParameters } returns emptyMap()

        val response = azureFunction.run(requestMock, executionContextMock)

        assertThat(response.status).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("Hello azure functions!")
    }

    @Test
    internal fun `call hello endpoint with query param`() {
        every { requestMock.queryParameters } returns mapOf("name" to "larmic")

        val response = azureFunction.run(requestMock, executionContextMock)

        assertThat(response.status).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("Hello larmic!")
    }

    private fun HttpStatus.wrapByHttpResponseMessageBuilderMock() = HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(this)
}
