package com.example.islandgame.data

data class LevelConfig(
    val levelNumber: Int,
    val targetRequired: Int,
    val targetGem: Gems,
    val moves: Int,
)