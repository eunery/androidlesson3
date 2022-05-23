package com.example.androidlesson3.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.androidlesson3.viewmodels.MainViewModel
import com.example.androidlesson3.viewmodels.ViewModelFactory
import com.example.data.repository.BalanceRepository
import com.example.data.repository.TariffRepository
import com.example.data.repository.UserRepository
import com.example.domain.repository.IBalanceRepository
import com.example.domain.repository.ITariffRepository
import com.example.domain.repository.IUserRepository
import com.example.domain.usecases.getBalance.GetBalanceUseCase
import com.example.domain.usecases.getBalance.IGetBalanceUseCase
import com.example.domain.usecases.getTariffs.DeleteTariffUseCase
import com.example.domain.usecases.getTariffs.GetTariffsUseCase
import com.example.domain.usecases.getTariffs.IDeleteTariffUseCase
import com.example.domain.usecases.getTariffs.IGetTariffsUseCase
import com.example.domain.usecases.getUser.GetUserUseCase
import com.example.domain.usecases.getUser.IGetUserUseCase
import com.example.network.retrofit.ApiProvider
import com.example.network.retrofit.IApi
import com.example.data.Database
import com.example.data.migrations.Migration_1_2
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

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

    @Binds abstract fun bindDeleteTariffUseCase(usecase: DeleteTariffUseCase): IDeleteTariffUseCase
}

@Module
class AppModule(private val context: Context) {
    @Provides fun provideContext() = context
    @Provides
    fun provideApi(apiProvider: ApiProvider): IApi =
        apiProvider.getApi()
    @Provides
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, Database::class.java, "db")
            .addMigrations(Migration_1_2).build()

    @Provides fun provideBalanceDao(database: Database) = database.getBalanceDao()
    @Provides fun provideTariffDao(database: Database) = database.getTariffDao()
    @Provides fun provideUserDao(database: Database) = database.getUserDao()
}