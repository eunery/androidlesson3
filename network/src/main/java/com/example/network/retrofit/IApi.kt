package com.example.network.retrofit

import com.example.domain.model.Balance
import com.example.domain.model.Tariff
import com.example.domain.model.User
import retrofit2.http.GET

interface IApi
{
    @GET("user")
    suspend fun getUserInfo(): List<User>

    @GET("tariff")
    suspend fun getTariffInfo():List<Tariff>

    @GET("balance")
    suspend fun getBalanceInfo():List<Balance>

}