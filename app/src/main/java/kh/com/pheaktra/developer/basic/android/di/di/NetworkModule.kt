package kh.com.pheaktra.developer.basic.android.di.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kh.com.pheaktra.developer.basic.android.di.impl.UserRepositoryImpl
import kh.com.pheaktra.developer.basic.android.domain.repository.UserRepository
import kh.com.pheaktra.developer.basic.android.network.ApiService
import kh.com.pheaktra.developer.basic.android.network.RetrofitClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.instance
    }
}