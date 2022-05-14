package com.example.androidlesson3

import android.app.Application
import android.content.Context
import com.example.androidlesson3.di.DaggerIAppComponent
import com.example.androidlesson3.di.IAppComponent

class App: Application() {
    lateinit var appComponent: IAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerIAppComponent.create()
    }
}

fun Context.findAppComponent(): IAppComponent =
    (this.applicationContext as App).appComponent