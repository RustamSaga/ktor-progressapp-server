package com.progress.rustsaga94.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class PersonRequest(
    val nameUser: String,
    val personId: String,
    val name: String
)