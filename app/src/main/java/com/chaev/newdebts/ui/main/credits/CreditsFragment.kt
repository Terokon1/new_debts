package com.chaev.newdebts.ui.main.credits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaev.newdebts.databinding.FragmentCreditsBinding
import com.chaev.newdebts.ui.main.DebtsCreditsAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
class CreditsFragment : Fragment() {
    private val adapter = DebtsCreditsAdapter()
    private lateinit var binding: FragmentCreditsBinding
    private val creditsViewModel: CreditsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreditsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.debtsList.adapter = adapter
        binding.debtsList.layoutManager = LinearLayoutManager(requireContext())
        creditsViewModel.viewModelScope.launch {
            creditsViewModel.credits.collect {
                adapter.items = it
                adapter.notifyDataSetChanged()
            }
        }
    }
}