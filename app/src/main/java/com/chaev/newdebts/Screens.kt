package com.chaev.newdebts

import com.chaev.newdebts.ui.login.LoginFragment
import com.chaev.newdebts.ui.main.MainFragment
import com.chaev.newdebts.ui.registration.RegistrationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Login() = FragmentScreen { LoginFragment() }
    fun Main() = FragmentScreen { MainFragment() }
    fun Registrsation() = FragmentScreen { RegistrationFragment() }
}