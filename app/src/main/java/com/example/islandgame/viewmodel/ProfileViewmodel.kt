package com.example.islandgame.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islandgame.repository.ProfileRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewmodel (private val repository: ProfileRepo) : ViewModel(){

    val username: StateFlow<String> = repository.usernameFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Player 1"
        )

    val country: StateFlow<String> = repository.countryFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Brazil"
        )


    fun updateprofile (newName: String, newCountry: String){
        viewModelScope.launch {
            repository.saveProfile(newName, newCountry)
        }
    }
}