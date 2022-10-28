package de.larmic.azure

import com.microsoft.azure.functions.*
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.Optional
import java.util.logging.Logger
import kotlin.collections.HashMap

/**
 * Unit test for Function class.
 */
class FunctionTest {

    inline fun <reified T : Any> mock() = mock(T::class.java)

    @Test
    fun testHttpTrigger() {
        // Setup
        val req = mock<HttpRequestMessage<Optional<String>>>()

        val queryParams = HashMap<String, String>()
        queryParams["name"] = "Azure"
        doReturn(queryParams).`when`(req).queryParameters

        doReturn(HttpMethod.GET).`when`<HttpRequestMessage<*>>(req).httpMethod

        doAnswer { invocation ->
            val status = invocation.arguments[0] as HttpStatus
            HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(status)
        }.`when`<HttpRequestMessage<*>>(req).createResponseBuilder(any(HttpStatus::class.java))

        val context = mock(ExecutionContext::class.java)
        doReturn(Logger.getGlobal()).`when`(context).logger

        // Invoke
        val ret = Function().run(req, context)

        // Verify
        assertEquals(ret.status, HttpStatus.OK)
    }

}
