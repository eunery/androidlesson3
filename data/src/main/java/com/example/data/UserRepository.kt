package com.example.data

import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import com.example.network.retrofit.ApiProvider
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiProvider: ApiProvider
):IUserRepository {
    override suspend fun getUser(): List<User> {
        return apiProvider.getApi().getUserInfo()
    }
}