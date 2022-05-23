package com.example.data.repository

import com.example.data.dao.UserDao
import com.example.data.model.BalanceEntity
import com.example.data.model.UserEntity
import com.example.domain.model.Balance
import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import com.example.network.retrofit.ApiProvider
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiProvider: ApiProvider,
    private val userDao: UserDao,
):IUserRepository {
    override suspend fun getUser(): List<User> {
        val fromDb = userDao.getAll()
        return if (fromDb.isNotEmpty()) {
            val entity = fromDb
            entity.map{entity-> User(entity.id, entity.names, entity.address) }
        } else {

            val fromApi = apiProvider.getApi().getUserInfo()
            val mapped = fromApi.map{fromApi-> UserEntity(fromApi.id, fromApi.names, fromApi.address) }
            userDao.saveAll(*mapped.toTypedArray())
            fromApi
        }
    }
}