package org.example.smartmeal.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.example.smartmeal.data.remote.createHttpClient
import org.example.smartmeal.ui.views.ai.ChatViewModel
import org.example.smartmeal.ui.views.bmi_form.BmiFormViewModel
import org.example.smartmeal.ui.views.cutlery.CutleryViewModel
import org.example.smartmeal.ui.views.favorite.FavoriteViewModel
import org.example.smartmeal.ui.views.health.HealthViewModel
import org.example.smartmeal.ui.views.home.HomeViewModel
import org.example.smartmeal.ui.views.login.LoginViewModel
import org.example.smartmeal.ui.views.own.OwnViewModel
import org.example.smartmeal.ui.views.profile.ProfileViewModel
import org.example.smartmeal.ui.views.register.RegisterViewModel
import org.example.smartmeal.ui.views.search.SearchViewModel
import org.example.smartmeal.ui.views.selection.SelectionViewModel
import org.example.smartmeal.ui.views.tdee_form.TdeeFormViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single { createHttpClient() }
    factory { LoginViewModel() }
    factory { RegisterViewModel() }
    factory { OwnViewModel() }
    factory { CutleryViewModel() }
    factory { ProfileViewModel() }
    factory { HealthViewModel() }
    factory { TdeeFormViewModel() }
    factory { BmiFormViewModel() }
    factory { HomeViewModel() }
    factory { SearchViewModel() }
    factory { FavoriteViewModel() }
    factory { ChatViewModel() }
    factory {
        SelectionViewModel(
            mealName = get(),
            selectedDate = get()
        )
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule)
        Napier.base(DebugAntilog())
    }