package com.chaev.newdebts.ui.main.credits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaev.newdebts.domain.models.Debts
import com.chaev.newdebts.domain.repositories.DebtsApiRepository
import com.chaev.newdebts.utils.Left
import com.chaev.newdebts.utils.Right
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreditsViewModel(private val debtsApiRepository: DebtsApiRepository) : ViewModel() {
    private val _credits: MutableStateFlow<List<Debts>> = MutableStateFlow(listOf())
    val credits: StateFlow<List<Debts>> = _credits

    init {
        getCredits()
    }

    private fun getCredits() {
        viewModelScope.launch {
            when (val r = debtsApiRepository.getCredits()) {
                is Right -> {
                    _credits.emit(r.value)
                }
                is Left -> {}
            }
        }

    }
}