package kh.com.pheaktra.developer.kmp.basic.domain.repository

import kh.com.pheaktra.developer.kmp.basic.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasks(): Flow<List<TaskModel>>

    suspend fun insertTask(task: TaskModel)

    suspend fun updateTask(task: TaskModel)

    suspend fun deleteTask(taskId: Long)
}