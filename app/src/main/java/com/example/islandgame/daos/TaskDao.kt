package com.example.islandgame.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.islandgame.databasestuff.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(task: TaskEntity)

    @Query("""
        UPDATE tasks
        SET completed = 1
        WHERE id = :taskId
    """)
    suspend fun completeTask(taskId: Int)
}