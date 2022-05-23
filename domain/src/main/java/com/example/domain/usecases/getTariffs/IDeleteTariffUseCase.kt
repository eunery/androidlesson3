package com.example.domain.usecases.getTariffs

import com.example.domain.model.Tariff

interface IDeleteTariffUseCase {
    suspend operator fun invoke(tariff: Tariff): List<Tariff>
}