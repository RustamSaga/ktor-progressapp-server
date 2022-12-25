package com.progress.rustsaga94.plugins

import com.progress.rustsaga94.data.UserDataSource
import com.progress.rustsaga94.routes.getPerson
import com.progress.rustsaga94.routes.getSecretInfo
import com.progress.rustsaga94.routes.signIn
import com.progress.rustsaga94.routes.signUp
import com.progress.rustsaga94.security.hashing.HashingService
import com.progress.rustsaga94.security.token.JwtTokenService
import com.progress.rustsaga94.security.token.TokenConfig
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: JwtTokenService,
    tokenConfig: TokenConfig
) {

    routing {
        signUp(hashingService = hashingService, userDataSource = userDataSource)
        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        getSecretInfo()

        getPerson(hashingService, userDataSource)
    }
}
