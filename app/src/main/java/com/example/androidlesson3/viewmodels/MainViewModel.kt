package com.example.androidlesson3.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Balance
import com.example.domain.model.Tariff
import com.example.domain.model.User
import com.example.domain.usecases.getBalance.IGetBalanceUseCase
import com.example.domain.usecases.getTariffs.IDeleteTariffUseCase
import com.example.domain.usecases.getTariffs.IGetTariffsUseCase
import com.example.domain.usecases.getUser.IGetUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val tariffsUseCase: IGetTariffsUseCase,
    private val userInfoUseCase: IGetUserUseCase,
    private val balanceUseCase: IGetBalanceUseCase,
    private val deleteTariffUseCase: IDeleteTariffUseCase,
): ViewModel(){
    val balance = MutableLiveData<List<Balance>>()
    val userInfo = MutableLiveData<List<User>>()
    val tariffs = MutableLiveData<List<Tariff>>()
    val isLoading = MutableLiveData(false)


    fun refreshData() {
        viewModelScope.launch {
            isLoading.value = true
            balance.value = balanceUseCase.getBalance()
            tariffs.value = tariffsUseCase.getTariffs()
            userInfo.value = userInfoUseCase.getUser()
            isLoading.value = false
        }
    }

    fun delete(tariff: Tariff) {
        viewModelScope.launch {
            isLoading.value = true
            val newList = deleteTariffUseCase(tariff)
            tariffs.value = newList
            isLoading.value = false
        }
    }
}
