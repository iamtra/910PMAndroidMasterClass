package kh.com.pheaktra.developer.basic.android.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kh.com.pheaktra.developer.basic.android.network.remote.ApiService
import kh.com.pheaktra.developer.basic.android.network.remote.RetrofitClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.instance
    }
}