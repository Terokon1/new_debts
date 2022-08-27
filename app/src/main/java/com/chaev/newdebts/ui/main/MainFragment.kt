package com.chaev.newdebts.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chaev.newdebts.databinding.FragmentMainBinding
import com.chaev.newdebts.ui.base.BaseFragment
import com.chaev.newdebts.ui.base.IBottomNavigable
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : BaseFragment(), IBottomNavigable {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = MainPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Вы должны"
                1 -> tab.text = "Вам должны"
            }
        }.attach()
    }
}
