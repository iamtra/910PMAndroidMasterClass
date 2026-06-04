package kh.com.pheaktra.developer.basic.android.di.local.dao

import androidx.room.*
import kh.com.pheaktra.developer.basic.android.di.local.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM Task WHERE id = :id")
    suspend fun getTaskById(id: String): Task

    @Insert
    suspend fun insertTask(task: Task)

    @Query("DELETE FROM Task WHERE id = :id")
    suspend fun deleteTask(id: String)

    @Update
    suspend fun updateTask(task: Task)
}