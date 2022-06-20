package com.chaev.newdebts.di

import com.chaev.newdebts.cicerone.CiceroneHolder
import com.chaev.newdebts.data.api.RetrofitBuilder
import com.chaev.newdebts.domain.repositories.DebtsApiRepository
import com.chaev.newdebts.ui.login.LoginViewModel
import com.chaev.newdebts.ui.registration.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { CiceroneHolder() }
    single { DebtsApiRepository(RetrofitBuilder.apiService) }
    viewModel { LoginViewModel(get<CiceroneHolder>().router, get<DebtsApiRepository>()) }
    viewModel { RegistrationViewModel(get<CiceroneHolder>().router, get<DebtsApiRepository>()) }


}