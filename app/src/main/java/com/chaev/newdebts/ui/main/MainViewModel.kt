package com.chaev.newdebts.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaev.newdebts.domain.models.Sums
import com.chaev.newdebts.domain.repositories.DebtsApiRepository
import com.chaev.newdebts.utils.Left
import com.chaev.newdebts.utils.Right
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val debtsApiRepository: DebtsApiRepository) : ViewModel() {
    private val _sums = MutableStateFlow<Sums>(Sums(0.0, 0.0))
    val sums: StateFlow<Sums> = _sums

    init {
        getSums()
    }

    private fun getSums() {
        viewModelScope.launch {
            when (val r = debtsApiRepository.getSums()) {
                is Right -> {
                    _sums.emit(r.value)
                }
                is Left -> {
                }
            }
        }
    }
}