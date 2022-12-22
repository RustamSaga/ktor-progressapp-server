package com.progress.rustsaga94.security.hashing

data class SaltedHash(
    val hash: String,
    val salt: String
)