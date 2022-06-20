package com.chaev.newdebts

import com.chaev.newdebts.ui.login.LoginFragment
import com.chaev.newdebts.ui.main.MainFragment
import com.chaev.newdebts.ui.registration.RegistrationStep1Fragment
import com.chaev.newdebts.ui.registration.RegistrationStep2Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Login() = FragmentScreen { LoginFragment() }
    fun Main() = FragmentScreen { MainFragment() }
    fun Registrsation1() = FragmentScreen { RegistrationStep1Fragment() }
    fun Registrsation2() = FragmentScreen { RegistrationStep2Fragment() }
}