package kh.com.pheaktra.developer.kmp.basic.di.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kh.com.pheaktra.developer.kmp.basic.di.data.local.dao.TaskDao
import kh.com.pheaktra.developer.kmp.basic.di.data.local.entity.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}