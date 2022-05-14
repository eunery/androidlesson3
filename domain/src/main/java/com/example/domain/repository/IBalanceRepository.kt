package com.example.domain.repository

import com.example.domain.model.Balance

interface IBalanceRepository {
    suspend fun getBalance(): List<Balance>
}