package com.example.islandgame.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_profile")

class ProfileRepo(private val context: Context){
    companion object {
        val KEY_NAME = stringPreferencesKey("username")
        val KEY_COUNTRY = stringPreferencesKey("country")
    }

    val usernameFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs [KEY_NAME] ?: "Player 1"
    }

    val countryFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs [KEY_COUNTRY] ?: "Brazil"
    }

    suspend fun saveProfile (username: String, country: String){
        context.dataStore.edit { prefs ->
            prefs [KEY_NAME] = username
            prefs [KEY_COUNTRY] = country

        }
    }
}