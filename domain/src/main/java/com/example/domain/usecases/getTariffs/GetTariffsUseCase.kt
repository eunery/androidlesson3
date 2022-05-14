package com.example.domain.usecases.getTariffs

import javax.inject.Inject
import com.example.domain.model.Tariff
import com.example.domain.repository.ITariffRepository

class GetTariffsUseCase @Inject constructor(
    private val repo: ITariffRepository
    ): IGetTariffsUseCase {
    override suspend fun getTariffs(): List<Tariff> {
        return repo.getTariffs()
    }
}