package com.chaev.newdebts.di

import com.chaev.newdebts.cicerone.CiceroneHolder
import com.chaev.newdebts.data.SharedPrefManager
import com.chaev.newdebts.data.api.RetrofitBuilder
import com.chaev.newdebts.domain.repositories.DebtsApiRepository
import com.chaev.newdebts.ui.activity.MainActivityViewModel
import com.chaev.newdebts.ui.login.LoginViewModel
import com.chaev.newdebts.ui.main.MainViewModel
import com.chaev.newdebts.ui.main.credits.CreditsViewModel
import com.chaev.newdebts.ui.main.debts.DebtsViewModel
import com.chaev.newdebts.ui.registration.RegistrationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { CiceroneHolder() }
    single { DebtsApiRepository(RetrofitBuilder.apiService, get<SharedPrefManager>()) }
    single { SharedPrefManager(androidContext()) }
    viewModel { LoginViewModel(get<CiceroneHolder>().router, get<DebtsApiRepository>()) }
    viewModel { RegistrationViewModel(get<CiceroneHolder>().router, get<DebtsApiRepository>()) }
    viewModel { CreditsViewModel(get<DebtsApiRepository>()) }
    viewModel { DebtsViewModel(get<DebtsApiRepository>()) }
    viewModel { MainViewModel(get<DebtsApiRepository>()) }
    viewModel {
        MainActivityViewModel(
            get<CiceroneHolder>().router,
            get<DebtsApiRepository>(),
            get<SharedPrefManager>()
        )
    }

}