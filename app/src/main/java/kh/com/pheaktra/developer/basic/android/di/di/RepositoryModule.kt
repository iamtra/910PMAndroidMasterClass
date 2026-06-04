package kh.com.pheaktra.developer.basic.android.di.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kh.com.pheaktra.developer.basic.android.di.impl.TaskRepositoryImpl
import kh.com.pheaktra.developer.basic.android.di.impl.UserRepositoryImpl
import kh.com.pheaktra.developer.basic.android.domain.repository.TaskRepository
import kh.com.pheaktra.developer.basic.android.domain.repository.UserRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository {
        return impl
    }

    @Provides
    @Singleton
    fun bindTaskRepository(
        impl: TaskRepositoryImpl
    ) : TaskRepository {
        return impl
    }
}