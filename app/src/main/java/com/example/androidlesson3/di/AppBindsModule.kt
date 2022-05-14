package com.example.androidlesson3.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidlesson3.viewmodels.MainViewModel
import com.example.androidlesson3.viewmodels.ViewModelFactory
import com.example.data.BalanceRepository
import com.example.data.TariffRepository
import com.example.data.UserRepository
import com.example.domain.repository.IBalanceRepository
import com.example.domain.repository.ITariffRepository
import com.example.domain.repository.IUserRepository
import com.example.domain.usecases.getBalance.GetBalanceUseCase
import com.example.domain.usecases.getBalance.IGetBalanceUseCase
import com.example.domain.usecases.getTariffs.GetTariffsUseCase
import com.example.domain.usecases.getTariffs.IGetTariffsUseCase
import com.example.domain.usecases.getUser.GetUserUseCase
import com.example.domain.usecases.getUser.IGetUserUseCase
import com.example.network.retrofit.ApiProvider
import com.example.network.retrofit.IApi
import com.example.network.retrofit.RetrofitClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module(includes = [AppModule::class])
abstract class AppBindsModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindNewEventViewModel(newEventViewModel: MainViewModel): ViewModel

    @Binds abstract fun bindUserRepo(userRepository: UserRepository): IUserRepository


    @Binds abstract fun bindTariffRepo(tariffRepository: TariffRepository): ITariffRepository
    @Binds abstract fun bindBalanceRepo(balanceRepository: BalanceRepository): IBalanceRepository

    @Binds abstract fun bindUserUseCase(userUseCase: GetUserUseCase): IGetUserUseCase
    @Binds abstract fun bindTariffUseCase(tariffsUseCase: GetTariffsUseCase): IGetTariffsUseCase
    @Binds abstract fun bindBalanceUseCase(balanceUseCase: GetBalanceUseCase): IGetBalanceUseCase
}

@Module
class AppModule {
    @Provides
    fun provideApi(apiProvider: ApiProvider): IApi =
        apiProvider.getApi()
}