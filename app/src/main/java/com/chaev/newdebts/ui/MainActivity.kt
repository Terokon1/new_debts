package com.chaev.newdebts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import com.chaev.newdebts.R
import com.chaev.newdebts.Screens
import com.chaev.newdebts.cicerone.CiceroneHolder
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val cicerone: CiceroneHolder by inject()
    private val navigatorHolder = cicerone.navigatorHolder

    //    private val navigator = AppNavigator(this, R.id.fragment_container, supportFragmentManager)
    private val navigator =
        object : AppNavigator(this, R.id.fragment_container, supportFragmentManager) {
            override fun setupFragmentTransaction(
                screen: FragmentScreen,
                fragmentTransaction: FragmentTransaction,
                currentFragment: Fragment?,
                nextFragment: Fragment
            ) {
                super.setupFragmentTransaction(
                    screen,
                    fragmentTransaction,
                    currentFragment,
                    nextFragment
                )
                if (currentFragment == null) {
                    return
                }
                fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            cicerone.router.replaceScreen(Screens.Login())
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}