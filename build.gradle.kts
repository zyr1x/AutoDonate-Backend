plugins {
    id("application")
    kotlin("jvm") version "2.0.0"
    id("com.google.devtools.ksp") version ("2.0.21-1.0.25")
}

application {
    applicationName = "AutoDonate"
    mainClass.set("ru.lewis.MainKt")
    applicationDefaultJvmArgs = listOf("-Dfile.encoding=UTF-8")
}

val koraBom: Configuration by configurations.creating
configurations {
    ksp.get().extendsFrom(koraBom)
    compileOnly.get().extendsFrom(koraBom)
    api.get().extendsFrom(koraBom)
    implementation.get().extendsFrom(koraBom)
}

group = "ru.lewis"
version = "1.0"

repositories {
    mavenCentral()
}

val koraVersion: String = "0.14.1"

dependencies {
    koraBom(platform("ru.tinkoff.kora:kora-parent:$koraVersion"))

    ksp("ru.tinkoff.kora:symbol-processors")

    implementation("ru.tinkoff.kora:http-server-undertow")
    implementation("ru.tinkoff.kora:json-module")
    implementation("ru.tinkoff.kora:config-hocon")
    implementation("ru.tinkoff.kora:logging-logback")

    testImplementation("ru.tinkoff.kora:test-junit5")
    testImplementation("org.skyscreamer:jsonassert:1.5.1")
    testImplementation("org.testcontainers:junit-jupiter:1.19.8")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.9.0")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
    sourceSets.main { kotlin.srcDir("build/generated/ksp/main/kotlin") }
    sourceSets.test { kotlin.srcDir("build/generated/ksp/test/kotlin") }
}