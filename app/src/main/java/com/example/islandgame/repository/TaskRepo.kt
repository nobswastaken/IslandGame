package com.example.islandgame.repository

import android.content.Context
import com.example.islandgame.databasestuff.GameDatabase
import com.example.islandgame.databasestuff.TaskEntity

class TaskRepo(context: Context) {

    private val dao = GameDatabase
        .getDatabase(context)
        .taskDao()

    val tasksFlow = dao.getTasks()

    suspend fun completeTask(taskId: Int) {
        dao.completeTask(taskId)
    }

    suspend fun initializeTasks() {
        dao.saveTask(TaskEntity(id = 1))
        dao.saveTask(TaskEntity(id = 2))
    }
}