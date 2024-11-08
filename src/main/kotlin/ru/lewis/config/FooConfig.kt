package ru.lewis.config

import ru.lewis.config.type.Product
import ru.tinkoff.kora.config.common.annotation.ConfigSource


@ConfigSource("foo")
interface FooConfig {

    fun products(): List<Product>

}