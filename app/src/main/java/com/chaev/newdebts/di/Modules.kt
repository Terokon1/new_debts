package com.chaev.newdebts.di

import com.chaev.newdebts.cicerone.CiceroneHolder
import com.chaev.newdebts.data.api.RetrofitBuilder
import com.chaev.newdebts.domain.repositories.DebtsApiRepository
import com.chaev.newdebts.ui.login.LoginViewModel
import com.chaev.newdebts.ui.registration.step1.RegistrationStep1ViewModel
import com.chaev.newdebts.ui.registration.step2.RegistrationStep2ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { CiceroneHolder() }
    single { DebtsApiRepository(RetrofitBuilder.apiService) }
    viewModel { LoginViewModel(get<CiceroneHolder>().router, get<DebtsApiRepository>()) }
    viewModel { RegistrationStep1ViewModel(get<CiceroneHolder>().router, get<DebtsApiRepository>()) }
    viewModel { RegistrationStep2ViewModel(get<CiceroneHolder>().router, get<DebtsApiRepository>()) }

}