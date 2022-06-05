package com.chaev.newdebts.ui.registration.step1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chaev.newdebts.databinding.FragmentRegistrationStep1Binding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationStep1Fragment : Fragment() {

    private lateinit var binding: FragmentRegistrationStep1Binding
    private val viewModel: RegistrationStep1ViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationStep1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}