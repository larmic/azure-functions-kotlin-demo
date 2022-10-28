package de.larmic.azure

import com.microsoft.azure.functions.*

/**
 * The mock for HttpResponseMessage, can be used in unit tests to verify if the
 * returned response by HTTP trigger function is correct or not.
 */
class HttpResponseMessageMock(
    private val httpStatus: HttpStatusType,
    private val headers: Map<String, String>,
    private val body: String
) : HttpResponseMessage {

    override fun getStatus() = this.httpStatus
    override fun getStatusCode() = httpStatus.value()
    override fun getHeader(key: String) = this.headers[key]
    override fun getBody() = this.body

    class HttpResponseMessageBuilderMock(var httpStatus: HttpStatusType) : HttpResponseMessage.Builder {
        private var body: Any? = null
        private val headers = mutableMapOf<String, String>()

        override fun status(httpStatusType: HttpStatusType): HttpResponseMessage.Builder {
            this.httpStatus = httpStatusType
            return this
        }

        override fun header(key: String, value: String): HttpResponseMessage.Builder {
            this.headers[key] = value
            return this
        }

        override fun body(body: Any): HttpResponseMessage.Builder {
            this.body = body
            return this
        }

        override fun build() = HttpResponseMessageMock(this.httpStatus, this.headers, this.body.toString())
    }
}
