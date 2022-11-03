package de.larmic.azure

import com.microsoft.azure.functions.*
import de.larmic.azure.HttpResponseMessageMock.HttpResponseMessageBuilderMock
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.Optional
import java.util.logging.Logger

/**
 * This is an integration tests.
 * TODO find a way to start azure function in a container and call GET {url} instead of mocking!
 */
@DisplayName("Call hello azure function with")
class HelloAzureFunctionIT {

    // the azure function to test
    private val helloAzureFunction = HelloAzureFunction()

    // request mock with default behavior
    private val requestMock = mockk<HttpRequestMessage<Optional<String>>> {
        every { httpMethod } returns HttpMethod.GET
        every { createResponseBuilder(any()) } answers {
            firstArg<HttpStatus>().wrapInHttpResponseMessageBuilderMock()
        }
    }

    // execution context mock with default behavior
    private val executionContextMock = mockk<ExecutionContext> {
        every { logger } returns Logger.getGlobal()
    }

    @Test
    internal fun `query parameter is missing`() {
        every { requestMock.queryParameters } returns emptyMap()

        val response = helloAzureFunction.run(requestMock, executionContextMock)

        response.status shouldBe HttpStatus.OK
        response.body shouldBe "Hello azure functions!"
    }

    @Test
    internal fun `query parameter is set`() {
        every { requestMock.queryParameters } returns mapOf("name" to "larmic")

        val response = helloAzureFunction.run(requestMock, executionContextMock)

        response.status shouldBe HttpStatus.OK
        response.body shouldBe "Hello larmic!"
    }

    private fun HttpStatus.wrapInHttpResponseMessageBuilderMock() = HttpResponseMessageBuilderMock(this)
}
