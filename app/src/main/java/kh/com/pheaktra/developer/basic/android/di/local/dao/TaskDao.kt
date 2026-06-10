package kh.com.pheaktra.developer.basic.android.di.local.dao

import androidx.room.*
import kh.com.pheaktra.developer.basic.android.di.local.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE id = :id")
    suspend fun getTaskById(id: String): Task

    @Insert
    suspend fun insertTask(task: Task)

    @Query("DELETE FROM Task WHERE id = :id")
    suspend fun deleteTask(id: Long)

    @Update
    suspend fun updateTask(task: Task)
}