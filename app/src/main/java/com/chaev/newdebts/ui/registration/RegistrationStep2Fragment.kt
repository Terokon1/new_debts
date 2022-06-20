package com.chaev.newdebts.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.lifecycle.viewModelScope
import com.chaev.newdebts.databinding.FragmentRegistrationStep2Binding
import com.chaev.newdebts.utils.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegistrationStep2Fragment : Fragment() {

    private lateinit var binding: FragmentRegistrationStep2Binding
    private val viewModel: RegistrationViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationStep2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonBack.setOnClickListener {
                viewModel.navigateBack()
            }
            buttonRegister.setOnClickListener {
                if (passwordField.editText!!.text.isNotEmpty()) {
                    viewModel.password = passwordField.editText?.text.toString()
                    viewModel.registerButtonClicked()
                } else {
                    passwordField.isErrorEnabled = true
                    passwordField.error = "Поле ввода пустое"
                }
            }
        }
        viewModel.viewModelScope.launch {
            viewModel.status.collect {
                when (it) {
                    Status.SUCCESS -> {
                        showSuccessMessage()
                    }
                    Status.ERROR -> {
                        showErrorMessage()
                    }
                    Status.EMPTY -> {

                    }
                }
            }
        }
    }

    private fun showSuccessMessage() {
        Toast.makeText(context, "Успешно зарегистрировано!", Toast.LENGTH_SHORT).show()
    }

    private fun showErrorMessage() {
        Toast.makeText(context, "Регистрация не удалась :(", Toast.LENGTH_SHORT).show()
    }
}