package com.progress.rustsaga94.security.token

data class TokenConfig(
    val issuer: String,
    val audience: String,
    val exiresIn: Long,
    val secret: String
)