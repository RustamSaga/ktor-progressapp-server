package com.progress.rustsaga94.security.token

interface TokenService {

    fun generate(
        config: TokenConfig,
        vararg claim: TokenClaim
    ): String
}