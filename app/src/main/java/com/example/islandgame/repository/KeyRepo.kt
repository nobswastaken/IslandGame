package com.example.islandgame.repository

import android.content.Context
import com.example.islandgame.databasestuff.GameDatabase
import com.example.islandgame.databasestuff.KeysEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KeyRepo(context: Context) {

    private val dao = GameDatabase
        .getDatabase(context)
        .keyDao()

    val keysFlow: Flow<KeysEntity> =
        dao.getKeys()
            .map { it ?: KeysEntity() }

    suspend fun initializeKeys() {
        if (dao.getKeysOnce() == null) {
            dao.insertKeys(KeysEntity())
        }
    }

    suspend fun addKey() {
        dao.addKey()
    }

    suspend fun spendKey() {
        dao.spendKey()
    }
}