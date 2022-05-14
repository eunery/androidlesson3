package com.example.androidlesson3.di

import com.example.androidlesson3.ui.MainFragment
import com.example.androidlesson3.viewmodels.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = [
    AppBindsModule::class,
])
interface IAppComponent {
    fun viewModelFactory(): ViewModelFactory
}