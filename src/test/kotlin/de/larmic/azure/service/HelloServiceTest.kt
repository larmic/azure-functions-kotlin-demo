package de.larmic.azure.service

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Say hello with")
internal class HelloServiceTest {

    private val helloService = HelloService()

    @Test
    internal fun `name is set`() {
        val hello = helloService sayHelloTo "larmic"

        hello shouldBe "Hello larmic!"
    }

    @Test
    internal fun `name is missing`() {
        val hello = helloService sayHelloTo null

        hello shouldBe "Hello azure functions!"
    }
}