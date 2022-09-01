package com.chaev.newdebts.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaev.newdebts.Screens
import com.chaev.newdebts.data.SharedPrefManager
import com.chaev.newdebts.domain.repositories.DebtsApiRepository
import com.chaev.newdebts.utils.Left
import com.chaev.newdebts.utils.Right
import com.chaev.newdebts.utils.Status
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val router: Router,
    private val debtsApiRepository: DebtsApiRepository,
    private val prefManager: SharedPrefManager
) : ViewModel() {
    private val _loginStatus = MutableStateFlow<Status>(Status.PENDING)
    val loginStatus: StateFlow<Status> = _loginStatus

    fun checkAuthorization() {
        viewModelScope.launch {
            when (debtsApiRepository.verifyToken()) {
                is Right -> {
                    debtsApiRepository.getNewTokens()
                    prefManager.loadUser()
                    _loginStatus.emit(Status.SUCCESS)
                    router.replaceScreen(Screens.Main())
                }
                is Left -> {
                    _loginStatus.emit(Status.SUCCESS)
                    prefManager.clearToken()
                    prefManager.clearUser()
                    router.replaceScreen(Screens.Login())
                }
            }
        }
    }

    fun saveToken(){
        prefManager.updateToken(debtsApiRepository.refreshToken)
    }

    fun saveUsername(){
        prefManager.saveUser()
    }

}