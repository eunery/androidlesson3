package com.example.domain.usecases.getBalance

import com.example.domain.model.Balance
import com.example.domain.repository.IBalanceRepository
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor(
    private val repo: IBalanceRepository
):IGetBalanceUseCase {
    override suspend fun getBalance(): List<Balance> {
        return repo.getBalance()
    }
}