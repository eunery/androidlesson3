package com.example.domain.usecases.getUser

import com.example.domain.model.User

interface IGetUserUseCase {
    suspend fun getUser(): List<User>
}