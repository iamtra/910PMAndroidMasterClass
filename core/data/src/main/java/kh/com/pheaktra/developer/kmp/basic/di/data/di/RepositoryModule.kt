package kh.com.pheaktra.developer.kmp.basic.di.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kh.com.pheaktra.developer.kmp.basic.domain.repository.TaskRepository
import kh.com.pheaktra.developer.kmp.basic.domain.repository.UserRepository
import kh.com.pheaktra.developer.kmp.basic.di.data.impl.TaskRepositoryImpl
import kh.com.pheaktra.developer.kmp.basic.di.data.impl.UserRepositoryImpl

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