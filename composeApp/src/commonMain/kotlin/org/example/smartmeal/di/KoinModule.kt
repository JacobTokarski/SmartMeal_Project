package org.example.smartmeal.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.example.smartmeal.data.remote.createHttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single { createHttpClient() }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule)
        Napier.base(DebugAntilog())
    }