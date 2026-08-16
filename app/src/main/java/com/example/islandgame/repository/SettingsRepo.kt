package com.example.islandgame.repository

import android.content.Context
import com.example.islandgame.databasestuff.GameDatabase
import com.example.islandgame.databasestuff.SettingsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn

class SettingsRepo(private val context: Context) {
    private val dao = GameDatabase.getDatabase(context).settingsDao()

    val settingsFlow: Flow<SettingsEntity> = dao.getSettingsFlow()
        .filterNotNull()
        .flowOn(Dispatchers.IO)

    suspend fun settingsExists(){
        val settings = dao.getSettingsFlow().first()
        if(settings == null){
            dao.saveSettings(SettingsEntity())
        }
    }

    suspend fun updateMusic(enabled: Boolean) {
        val currentSettings = dao.getSettingsFlow().first()

        if(currentSettings != null){
            dao.saveSettings(currentSettings.copy(music = enabled)
            )
        }
    }

    suspend fun updateSound(enabled: Boolean) {
        val currentSettings = dao.getSettingsFlow().first()

        if(currentSettings != null){
            dao.saveSettings(currentSettings.copy(sound = enabled)
            )
        }
    }
}