package com.chaev.newdebts

import com.chaev.newdebts.ui.login.LoginFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Login() = FragmentScreen { LoginFragment() }
}