package de.larmic.azure.service

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class HelloServiceDelegateTest {

    // using a hello service mock returning name argument as response
    private val helloServiceMock = mockk<HelloService> {
        every { sayHelloTo(any()) } returnsArgument 0
    }

    private val helloServiceDelegate = HelloServiceDelegate(helloService = helloServiceMock)

    @Test
    internal fun `call delegate`() {
        val hello = helloServiceDelegate sayHelloTo "larmic"

        hello shouldBe "larmic"
    }
}