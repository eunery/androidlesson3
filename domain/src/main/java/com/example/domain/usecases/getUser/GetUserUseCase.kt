package com.example.domain.usecases.getUser

import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repo: IUserRepository
): IGetUserUseCase {
    override suspend fun getUser(): List<User> {
        return repo.getUser()
    }
}