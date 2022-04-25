package com.example.androidlesson3.data.api

class ApiProvider(private val client: RetrofitClient) {
    private val baseUrl = "https://625eb4993b039517f1fac74e.mockapi.io/androidlesson/"

    fun getApi() : IApi =
        client.getClient(baseUrl)
            .create(IApi::class.java)
}