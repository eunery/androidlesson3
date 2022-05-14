package com.example.domain.usecases.getBalance

import com.example.domain.model.Balance

interface IGetBalanceUseCase {
    suspend fun getBalance(): List<Balance>
}