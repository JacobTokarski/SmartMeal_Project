package org.example.smartmeal.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.example.smartmeal.data.remote.createHttpClient
import org.example.smartmeal.ui.views.login.LoginViewModel
import org.example.smartmeal.ui.views.own.OwnViewModel
import org.example.smartmeal.ui.views.register.RegisterViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single { createHttpClient() }
    factory { LoginViewModel() }
    factory { RegisterViewModel() }
    factory { OwnViewModel() }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule)
        Napier.base(DebugAntilog())
    }