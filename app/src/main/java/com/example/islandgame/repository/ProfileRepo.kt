package com.example.islandgame.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.islandgame.databasestuff.GameDatabase
import com.example.islandgame.databasestuff.UserProfileEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class ProfileRepo(private val context: Context){
    private val dao = GameDatabase.getDatabase(context).userProfileDao()

    val usernameFlow: Flow<String> = dao.getProfileFlow()
        .map { entity -> entity?.username ?: "Player 1" }
        .flowOn(Dispatchers.IO)

    val countryFlow: Flow<String> = dao.getProfileFlow()
        .map { entity -> entity?.country ?: "Brazil" }
        .flowOn(Dispatchers.IO)

   suspend fun ProfileExists() {
       val profile = dao.getProfileFlow().first()

       if (profile == null) {
           dao.saveProfile(UserProfileEntity())
       }
   }

    suspend fun saveProfile(username: String, country: String) {
        val updatedProfile = UserProfileEntity(
            id = 1,
            username = username,
            country = country
        )
        dao.saveProfile(updatedProfile)
    }
}