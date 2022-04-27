package com.example.androidlesson3.data.api

import com.example.androidlesson3.data.model.Balance
import com.example.androidlesson3.data.model.Tariff
import com.example.androidlesson3.data.model.User
import retrofit2.http.GET
import retrofit2.Call

interface IApi
{
    @GET("user")
    suspend fun getUserInfo(): List<User>

    @GET("tariff")
    suspend fun getTariffInfo():List<Tariff>

    @GET("balance")
    suspend fun getBalanceInfo():List<Balance>

}