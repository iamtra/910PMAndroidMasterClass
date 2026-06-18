package kh.com.pheaktra.developer.kmp.basic.di.data.impl

import kh.com.pheaktra.developer.kmp.basic.di.data.local.dao.TaskDao
import kh.com.pheaktra.developer.kmp.basic.di.data.local.entity.toTask
import kh.com.pheaktra.developer.kmp.basic.di.data.local.entity.toTaskModel
import kh.com.pheaktra.developer.kmp.basic.domain.model.TaskModel
import kh.com.pheaktra.developer.kmp.basic.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun getAllTasks(): Flow<List<TaskModel>> {
        return taskDao.getAllTasks().map { it.toTaskModel() }
    }

    override suspend fun insertTask(task: TaskModel) {
        taskDao.insertTask(task.toTask())
    }

    override suspend fun updateTask(task: TaskModel) {
        withContext(Dispatchers.IO) {
            taskDao.updateTask(task.toTask())
        }
    }

    override suspend fun deleteTask(taskId: Long) {
        taskDao.deleteTask(taskId)
    }
}