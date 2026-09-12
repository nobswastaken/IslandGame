package com.example.islandgame.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islandgame.repository.KeyRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class KeyViewmodel (private val keyRepo: KeyRepo) : ViewModel(){
    val keyCounter: StateFlow<Int> = keyRepo.keysFlow
        .map { entity -> entity.count }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0 // Safe default value while the database loads
        )
}
