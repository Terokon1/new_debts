package com.chaev.newdebts.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isNotEmpty
import com.chaev.newdebts.databinding.FragmentRegistrationStep1Binding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegistrationStep1Fragment : Fragment() {

    private lateinit var binding: FragmentRegistrationStep1Binding
    private val viewModel: RegistrationViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationStep1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonNext.setOnClickListener {
                if (emailField.editText!!.text.isNotEmpty()){
                    viewModel.email = emailField.editText?.text.toString()
                    viewModel.navigateNext()
                } else {
                    emailField.isErrorEnabled = true
                    emailField.error = "Поле ввода пустое"
                }
            }
            loginTv.setOnClickListener {
                viewModel.navigateLogin()
            }
        }
    }

}