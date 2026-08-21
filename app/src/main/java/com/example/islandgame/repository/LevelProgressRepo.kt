package com.example.islandgame.repository

import android.content.Context
import com.example.islandgame.databasestuff.GameDatabase
import com.example.islandgame.databasestuff.LevelProgressEntity

class LevelProgressRepo(context: Context) {
    private val dao = GameDatabase
        .getDatabase(context)
        .levelProgressDao()

    suspend fun getAllLevelProgress(): List<LevelProgressEntity> {
        return dao.getAllLevelProgress()
    }

    suspend fun getNextLevel(): Int {
        val completedLevels = dao.getAllLevelProgress()
            .filter { it.stars > 0 }
            .map { it.levelNumber }
        return if (completedLevels.isEmpty()) {
            1
        } else {
            val highestCompletedLevel = completedLevels.maxOrNull() ?: 0
            highestCompletedLevel + 1
        }
    }

    suspend fun saveStars(levelNumber: Int, stars: Int) {
        val existingProgress = dao.getLevelProgress(levelNumber)

        if (existingProgress == null || stars > existingProgress.stars) {
            dao.saveLevelProgress(
                LevelProgressEntity(
                    levelNumber = levelNumber,
                    stars = stars
                )
            )
        }
    }

    suspend fun getStars(levelNumber: Int): Int {
        return dao.getLevelProgress(levelNumber)?.stars ?: 0
    }

    suspend fun saveLevelProgress(levelProgress: LevelProgressEntity) {
        dao.saveLevelProgress(levelProgress)
    }
}