package kh.com.pheaktra.developer.kmp.basic.di.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kh.com.pheaktra.developer.kmp.basic.di.data.local.AppDatabase
import kh.com.pheaktra.developer.kmp.basic.di.data.local.dao.TaskDao

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideTaskDao(
        appDatabase: AppDatabase
    ): TaskDao {
        return appDatabase.taskDao()
    }
}

/**
 * App start -> DB automatically open
 * App close -> DB automatically close
 *
 * When we need to use database, we will call the database to open and close after the task is completed
 */