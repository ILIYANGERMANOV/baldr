package logic

import io.ktor.client.engine.js.*
import rest.RestClient

fun restClient(): RestClient {
    return RestClient(
        JsClient()
    )
}