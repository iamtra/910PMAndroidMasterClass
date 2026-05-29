package kh.com.pheaktra.developer.basic.android.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kh.com.pheaktra.developer.basic.android.domain.repository.UserRepository
import kh.com.pheaktra.developer.basic.android.network.ApiService
import kh.com.pheaktra.developer.basic.android.network.RetrofitClient


@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    companion object {
        @Provides
        @Singleton
        fun provideApiService(): ApiService {
            return RetrofitClient.instance
        }
    }
}