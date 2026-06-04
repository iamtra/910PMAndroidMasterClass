package kh.com.pheaktra.developer.basic.android.di.impl

import kh.com.pheaktra.developer.basic.android.di.local.dao.TaskDao
import kh.com.pheaktra.developer.basic.android.di.local.entity.toTaskModel
import kh.com.pheaktra.developer.basic.android.domain.model.TaskModel
import kh.com.pheaktra.developer.basic.android.domain.model.toTask
import kh.com.pheaktra.developer.basic.android.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun getAllTasks(): List<TaskModel> {
        return taskDao.getAllTasks().toTaskModel()
    }

    override suspend fun insertTask(task: TaskModel) {
        taskDao.insertTask(task.toTask())
    }

    override suspend fun updateTask(task: TaskModel) {
        taskDao.updateTask(task.toTask())
    }

    override suspend fun deleteTask(taskId: String) {
        taskDao.deleteTask(taskId)
    }
}