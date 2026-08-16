package com.example.islandgame.databasestuff

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_table")
data class SettingsEntity(
    @PrimaryKey
    val id: Int = 1,
    val sound: Boolean = true,
    val music: Boolean = true
)
