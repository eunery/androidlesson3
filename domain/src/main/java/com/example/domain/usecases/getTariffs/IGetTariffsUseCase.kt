package com.example.domain.usecases.getTariffs

import com.example.domain.model.Tariff

interface IGetTariffsUseCase {
    suspend fun getTariffs(): List<Tariff>
}