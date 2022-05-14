package com.example.domain.repository

import com.example.domain.model.User

interface IUserRepository {
    suspend fun getUser(): List<User>
}