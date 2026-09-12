package com.example.islandgame.databasestuff

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.islandgame.daos.KeyDao
import com.example.islandgame.daos.SettingsDao
import com.example.islandgame.daos.UserProfileDao
import com.example.islandgame.daos.LevelProgressDao
import com.example.islandgame.daos.TaskDao

@Database(
    entities = [
        UserProfileEntity::class,
        SettingsEntity::class,
        LevelProgressEntity::class,
        KeysEntity::class,
        TaskEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun settingsDao(): SettingsDao

    abstract fun levelProgressDao(): LevelProgressDao

    abstract fun keyDao(): KeyDao
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "game_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}