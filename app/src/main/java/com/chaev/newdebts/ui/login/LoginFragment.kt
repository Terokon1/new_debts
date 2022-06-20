package com.chaev.newdebts.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.chaev.newdebts.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.scope.scope
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            val email = binding.emailField.editText?.text.toString()
            val password = binding.passwordField.editText?.text.toString()
            if (email.isEmpty()) {
                binding.emailField.isErrorEnabled = true
                binding.emailField.error = "Поле ввода пустое"
            } else {
                binding.emailField.isErrorEnabled = false
            }
            if (password.isEmpty()) {
                binding.passwordField.isErrorEnabled = true
                binding.passwordField.error = "Поле ввода пустое"
            } else {
                binding.passwordField.isErrorEnabled = false
            }
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.onLoginClicked(email, password)
            }
        }

        binding.registration.setOnClickListener {
            viewModel.navigateRegistration()
        }

        viewModel.viewModelScope.launch {
            viewModel.loginError.collect {
                if (it) {
                    showError()
                } else {
                    hideError()
                }
            }
        }
    }

    private fun showError() {
        binding.emailField.isErrorEnabled = true
        binding.passwordField.isErrorEnabled = true
        binding.passwordField.error = "Пара логин пароль не совпадают"
    }

    private fun hideError() {
        binding.emailField.isErrorEnabled = false
        binding.passwordField.isErrorEnabled = false
    }
}