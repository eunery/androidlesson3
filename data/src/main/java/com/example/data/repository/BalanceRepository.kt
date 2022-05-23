package com.example.data.repository

import com.example.data.dao.BalanceDao
import com.example.data.model.BalanceEntity
import com.example.domain.model.Balance
import com.example.domain.repository.IBalanceRepository
import com.example.network.retrofit.ApiProvider
import javax.inject.Inject

class BalanceRepository @Inject constructor(
    private val apiProvider: ApiProvider,
    private val balanceDao: BalanceDao,
):IBalanceRepository  {
    override suspend fun getBalance(): List<Balance> {
        val fromDb = balanceDao.getAll()
        return if (fromDb.isNotEmpty()) {
            val entity = fromDb
            entity.map{entity->Balance(entity.id, entity.amount, entity.toPay)}
        } else {

            val fromApi = apiProvider.getApi().getBalanceInfo()
            val mapped = fromApi.map{fromApi->BalanceEntity(fromApi.id, fromApi.amount, fromApi.toPay)}
            balanceDao.saveAll(*mapped.toTypedArray())
            fromApi
        }
    }
}