package com.progress.rustsaga94.data

import com.progress.rustsaga94.data.models.User

interface UserDataSource {

    suspend fun insertUser(user: User): Boolean

    suspend fun getUserByUsername(username: String): User?
}