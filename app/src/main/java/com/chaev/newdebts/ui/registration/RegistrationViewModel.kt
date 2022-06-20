package com.chaev.newdebts.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaev.newdebts.Screens
import com.chaev.newdebts.domain.repositories.DebtsApiRepository
import com.chaev.newdebts.utils.Left
import com.chaev.newdebts.utils.Right
import com.chaev.newdebts.utils.Status
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val router: Router,
    private val debtsApiRepository: DebtsApiRepository
) : ViewModel() {
    private val _status: MutableStateFlow<Status> = MutableStateFlow(Status.EMPTY)
    val status: StateFlow<Status> = _status
    var email = ""
    var password = ""


    fun navigateNext() {
        router.navigateTo(Screens.Registrsation2())
    }
    fun navigateBack(){
        router.exit()
    }
    fun navigateLogin(){
        router.replaceScreen(Screens.Login())
    }
    fun registerButtonClicked() {
        viewModelScope.launch {
            when(debtsApiRepository.register(email,password)){
                is Right -> {
                    _status.emit(Status.SUCCESS)
                    router.replaceScreen(Screens.Login())
                }
                is Left -> {
                    _status.emit(Status.EMPTY)
                    _status.emit(Status.ERROR)
                }
            }
        }
    }
}