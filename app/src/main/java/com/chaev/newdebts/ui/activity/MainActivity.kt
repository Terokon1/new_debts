package com.chaev.newdebts.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.chaev.newdebts.R
import com.chaev.newdebts.Screens
import com.chaev.newdebts.cicerone.CiceroneHolder
import com.chaev.newdebts.ui.base.IBottomNavigable
import com.chaev.newdebts.ui.base.IFragmentHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), IFragmentHolder {
    private val cicerone: CiceroneHolder by inject()
    private val navigatorHolder = cicerone.navigatorHolder
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
                fragmentTransaction.setTransition(TRANSIT_FRAGMENT_FADE)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.addOnBackStackChangedListener {
            supportFragmentManager.findFragmentById(R.id.fragment_container)?.let {
                onFragmentChanged(it)
            }
        }
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

    private fun onFragmentChanged(fragment: Fragment) {
        val botNavi = findViewById<BottomNavigationView>(R.id.bottom_navi)
        val line = findViewById<View>(R.id.line)
        botNavi.visibility = if (fragment is IBottomNavigable) View.VISIBLE else View.GONE
        line.visibility = if (fragment is IBottomNavigable) View.VISIBLE else View.GONE
    }

    override fun onAttach(fragment: Fragment) {
        onFragmentChanged(fragment)
    }
}