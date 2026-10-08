package org.example.smartmeal.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.datetime.LocalDate
import org.example.smartmeal.data.remote.MealDbApiService
import org.example.smartmeal.data.remote.createHttpClient
import org.example.smartmeal.data.repository.AuthenticationRepository
import org.example.smartmeal.data.repository.favorite.FavoriteRepository
import org.example.smartmeal.data.repository.home.CatalogRepository
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
    single { AuthenticationRepository() }
    single { MealDbApiService(httpClient = get()) }
    single { CatalogRepository(apiService = get()) }
    single { FavoriteRepository() }
    factory { LoginViewModel(authRepository = get()) }
    factory { RegisterViewModel(authRepository = get()) }
    factory { OwnViewModel() }
    factory { CutleryViewModel() }
    factory { ProfileViewModel(authRepository = get(), favoriteRepository = get()) }
    factory { HealthViewModel() }
    factory { TdeeFormViewModel() }
    factory { BmiFormViewModel() }
    factory { HomeViewModel(catalogRepository = get(), authRepository = get(), favoriteRepository = get()) }
    factory { SearchViewModel(catalogRepository = get(), favoriteRepository = get()) }
    factory { FavoriteViewModel(catalogRepository = get(), favoriteRepository = get()) }
    factory { ChatViewModel() }
    factory { (mealName: String, selectedDate: LocalDate) ->
        SelectionViewModel(
            mealName = mealName,
            selectedDate = selectedDate
        )
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule)
        Napier.base(DebugAntilog())
    }