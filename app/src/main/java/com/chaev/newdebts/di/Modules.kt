package com.chaev.newdebts.di

import com.chaev.newdebts.cicerone.CiceroneHolder
import org.koin.dsl.module

val appModule = module {
    single { CiceroneHolder() }
}