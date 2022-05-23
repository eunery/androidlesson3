package com.example.domain.repository

import com.example.domain.model.Tariff

interface ITariffRepository {
    suspend fun getTariffs(): List<Tariff>
    suspend fun deleteTariffs(tariff: Tariff): List<Tariff>
}