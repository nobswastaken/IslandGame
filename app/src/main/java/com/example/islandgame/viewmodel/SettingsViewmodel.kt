package com.example.islandgame.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islandgame.databasestuff.SettingsEntity
import com.example.islandgame.repository.SettingsRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewmodel(
    private val settingsRepo: SettingsRepo
): ViewModel() {

    init{
        viewModelScope.launch {
            settingsRepo.settingsExists()
        }
    }

    val settingsFlow: StateFlow<SettingsEntity> = settingsRepo.settingsFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SettingsEntity()
        )

    fun toggleMusic(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepo.updateMusic(enabled)
        }
    }

    fun toggleSound(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepo.updateSound(enabled)
        }
    }
}