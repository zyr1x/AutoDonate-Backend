package ru.lewis.controller

import ru.lewis.config.FooConfig
import ru.lewis.config.type.Product
import ru.tinkoff.kora.common.Component
import ru.tinkoff.kora.http.common.HttpMethod
import ru.tinkoff.kora.http.common.annotation.HttpRoute
import ru.tinkoff.kora.http.server.common.annotation.HttpController
import ru.tinkoff.kora.json.common.annotation.Json

@Component
@HttpController
class HttpController(
    private val config: FooConfig
) {

    @Json
    @HttpRoute(method = HttpMethod.POST, path = "/products")
    fun products(): List<Product> {
        return config.products()
    }

}