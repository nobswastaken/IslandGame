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