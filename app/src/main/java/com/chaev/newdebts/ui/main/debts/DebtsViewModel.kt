package com.chaev.newdebts.ui.main.debts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaev.newdebts.domain.models.Debts
import com.chaev.newdebts.domain.repositories.DebtsApiRepository
import com.chaev.newdebts.utils.Left
import com.chaev.newdebts.utils.Right
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DebtsViewModel(private val debtsApiRepository: DebtsApiRepository) : ViewModel() {
    private val _debts: MutableStateFlow<List<Debts>> = MutableStateFlow(listOf())
    val debts: StateFlow<List<Debts>> = _debts

    init {
        getDebts()
    }


    fun getDebts() {
        viewModelScope.launch {
            when (val r = debtsApiRepository.getDebts()) {
                is Right -> {
                    _debts.emit(r.value)
                }
                is Left -> {}
            }
        }

    }
}