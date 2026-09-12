package com.example.islandgame.databasestuff

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    val id: Int,
    val completed: Boolean = false
)