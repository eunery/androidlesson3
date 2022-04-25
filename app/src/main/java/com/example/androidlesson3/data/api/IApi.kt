package com.example.androidlesson3.data.api

import com.example.androidlesson3.data.model.Balance
import com.example.androidlesson3.data.model.Tariff
import com.example.androidlesson3.data.model.User
import retrofit2.http.GET
import retrofit2.Call

interface IApi
{
    @GET("user")
    fun getUserInfo():Call<List<User>>

    @GET("tariff")
    fun getTariffInfo():Call<List<Tariff>>

    @GET("balance")
    fun getBalanceInfo():Call<List<Balance>>

}