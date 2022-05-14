package com.example.data

import com.example.domain.model.Balance
import com.example.domain.repository.IBalanceRepository
import com.example.network.retrofit.ApiProvider
import javax.inject.Inject

class BalanceRepository @Inject constructor(
    private val apiProvider: ApiProvider
):IBalanceRepository  {
    override suspend fun getBalance(): List<Balance> {
        return apiProvider.getApi().getBalanceInfo()
    }
}