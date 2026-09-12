package com.example.islandgame.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.islandgame.databasestuff.KeysEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KeyDao {

    @Query("SELECT * FROM keys WHERE id = 1")
    fun getKeys(): Flow<KeysEntity?>

    @Query("SELECT * FROM keys WHERE id = 1 LIMIT 1")
    suspend fun getKeysOnce(): KeysEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(keys: KeysEntity)

    @Query("UPDATE keys SET count = count + 1 WHERE id = 1")
    suspend fun addKey()

    @Query("""
        UPDATE keys
        SET count = count - 1
        WHERE id = 1 AND count > 0
    """)
    suspend fun spendKey()
}