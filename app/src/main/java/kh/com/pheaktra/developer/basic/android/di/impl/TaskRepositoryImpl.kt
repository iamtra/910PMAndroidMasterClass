package kh.com.pheaktra.developer.basic.android.di.impl

import kh.com.pheaktra.developer.basic.android.di.local.dao.TaskDao
import kh.com.pheaktra.developer.basic.android.di.local.entity.toTaskModel
import kh.com.pheaktra.developer.basic.android.domain.model.TaskModel
import kh.com.pheaktra.developer.basic.android.domain.model.toTask
import kh.com.pheaktra.developer.basic.android.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override suspend fun getAllTasks(): List<TaskModel> {
        return withContext(Dispatchers.IO) {
            taskDao.getAllTasks().map { it.toTaskModel() }
        }
    }

    override suspend fun insertTask(task: TaskModel) {
        taskDao.insertTask(task.toTask())
    }

    override suspend fun updateTask(task: TaskModel) {
        withContext(Dispatchers.IO) {
            taskDao.updateTask(task.toTask())
        }
    }

    override suspend fun deleteTask(taskId: String) {
        taskDao.deleteTask(taskId)
    }
}