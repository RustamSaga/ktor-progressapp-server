package com.progress.rustsaga94

import com.progress.rustsaga94.data.MongoUserDataSource
import io.ktor.server.application.*
import com.progress.rustsaga94.plugins.*
import com.progress.rustsaga94.security.hashing.SHA256HashingService
import com.progress.rustsaga94.security.token.JwtTokenService
import com.progress.rustsaga94.security.token.TokenConfig
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val mongoPassword = System.getenv("MONGO_PASSWORD")
    val dbName = "ktor-progress"
    val db = KMongo.createClient(
        connectionString = "mongodb+srv://Rustam:$mongoPassword@cluster0.ellnejo.mongodb.net/?retryWrites=true&w=majority"
    )
        .coroutine
        .getDatabase(dbName)

    val userDataSource = MongoUserDataSource(db)
    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        exiresIn = 365L * 1000L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )
    val hashingService = SHA256HashingService()


    configureMonitoring()
    configureSerialization()
    configureSecurity(tokenConfig)
    configureRouting(
        userDataSource = userDataSource,
        hashingService = hashingService,
        tokenService = tokenService,
        tokenConfig = tokenConfig
    )
}
