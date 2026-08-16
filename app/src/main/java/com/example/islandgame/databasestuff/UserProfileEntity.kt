package com.example.islandgame.databasestuff

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userprofile_table")
data class UserProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val username: String = "Player 1",
    val country: String = "Brazil"
)
