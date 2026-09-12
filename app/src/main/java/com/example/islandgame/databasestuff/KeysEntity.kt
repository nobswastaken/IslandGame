package com.example.islandgame.databasestuff

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keys")
data class KeysEntity (
    @PrimaryKey
    val id: Int = 1,
    val count: Int = 0
)