package com.example.islandgame.databasestuff

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "levelprogress_table")
data class LevelProgressEntity(
    @PrimaryKey
    val levelNumber: Int,
    val stars: Int = 0,
)