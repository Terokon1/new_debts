package com.chaev.newdebts.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaev.newdebts.Screens
import com.chaev.newdebts.domain.repositories.DebtsApiRepository
import com.chaev.newdebts.utils.Left
import com.chaev.newdebts.utils.Right
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val router: Router,
    private val debtsApiRepository: DebtsApiRepository
) : ViewModel() {
    private val _loginError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loginError: StateFlow<Boolean> = _loginError
    fun onLoginClicked(email: String, password: String) {
        viewModelScope.launch {
            when (val r = debtsApiRepository.login(email, password)) {
                is Right -> {
                    debtsApiRepository.setupTokens(r.value)
                    navigateLogin()
                }
                is Left -> {
                    _loginError.emit(true)
                }
            }
        }
    }

    private fun navigateLogin() {
        router.replaceScreen(Screens.Main())
    }

    fun navigateRegistration() {
        router.navigateTo(Screens.Registrsation1())
    }
}