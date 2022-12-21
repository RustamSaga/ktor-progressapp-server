package com.progress.rustsaga94

import io.ktor.server.application.*
import com.progress.rustsaga94.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureSecurity()
    configureRouting()
}
