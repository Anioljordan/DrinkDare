package com.application.drinkdare.di


import AppModule
import android.app.Application
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(AppModule) // Carga el módulo de dependencias
        }
    }
}