package com.example.data

import com.example.domain.model.Tariff
import com.example.domain.repository.ITariffRepository
import com.example.network.retrofit.ApiProvider
import javax.inject.Inject

class TariffRepository @Inject constructor(
    private val apiProvider: ApiProvider
):ITariffRepository {
    override suspend fun getTariffs(): List<Tariff> {
       return apiProvider.getApi().getTariffInfo()
    }
}