package com.example.data.repository

import com.example.data.dao.TariffDao
import com.example.data.model.TariffEntity
import com.example.domain.model.Tariff
import com.example.domain.repository.ITariffRepository
import com.example.network.retrofit.ApiProvider
import javax.inject.Inject

class TariffRepository @Inject constructor(
    private val apiProvider: ApiProvider,
    private val tariffDao: TariffDao,
):ITariffRepository {
    override suspend fun getTariffs(): List<Tariff> {
        val fromDb = tariffDao.getAll()
        return if (fromDb.isNotEmpty()) {
            val entity = fromDb
            entity.map{entity-> Tariff(entity.id, entity.name, entity.description, entity.amount) }
        } else {
            val fromApi = apiProvider.getApi().getTariffInfo()
            val mapped = fromApi.map{fromApi-> TariffEntity(fromApi.id, fromApi.name, fromApi.description, fromApi.amount) }
            tariffDao.saveAll(*mapped.toTypedArray())
            fromApi
        }
    }

    override suspend fun deleteTariffs(tariff: Tariff): List<Tariff> {
        tariffDao.delete(TariffEntity(tariff.id, tariff.name, tariff.description, tariff.amount))
        return tariffDao.getAll().map { Tariff(it.id, it.name, it.description, it.amount) }
    }
}