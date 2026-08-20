package com.example.islandgame.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.islandgame.databasestuff.LevelProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelProgressDao {
    @Query("SELECT * FROM levelprogress_table WHERE levelNumber = :levelNumber")
    suspend fun getLevelProgress(levelNumber: Int): LevelProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLevelProgress(levelProgress: LevelProgressEntity)

    @Query("SELECT * FROM levelprogress_table")
    suspend fun getAllLevelProgress(): List<LevelProgressEntity>
}