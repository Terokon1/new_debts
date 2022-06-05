package com.chaev.newdebts.ui.registration.step2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chaev.newdebts.R

class RegistrationStep2Fragment : Fragment() {


    private lateinit var viewModel: RegistrationStep2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration_step2, container, false)
    }


}