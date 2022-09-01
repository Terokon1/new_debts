package com.chaev.newdebts.ui.main.debts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaev.newdebts.databinding.FragmentDebtsBinding
import com.chaev.newdebts.ui.main.DebtsCreditsAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DebtsFragment : Fragment() {
    private val adapter = DebtsCreditsAdapter()
    private lateinit var binding: FragmentDebtsBinding
    private val debtsViewModel: DebtsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDebtsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.debtsList.adapter = adapter
        binding.debtsList.layoutManager = LinearLayoutManager(requireContext())
        debtsViewModel.viewModelScope.launch {
            debtsViewModel.debts.collect {
                Log.d("zxc", "collect")
                adapter.items = it
                adapter.notifyDataSetChanged()
            }
        }
    }
}