package com.chaev.newdebts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaev.newdebts.R
import com.chaev.newdebts.Screens
import com.chaev.newdebts.cicerone.CiceroneHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val cicerone: CiceroneHolder by inject()
    private val navigatorHolder = cicerone.navigatorHolder
    private val navigator = AppNavigator(this, R.id.fragment_container, supportFragmentManager)
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