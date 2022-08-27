package com.chaev.newdebts.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chaev.newdebts.ui.main.credits.CreditsFragment
import com.chaev.newdebts.ui.main.debts.DebtsFragment

class MainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            DebtsFragment()
        } else {
            CreditsFragment()
        }
    }
}