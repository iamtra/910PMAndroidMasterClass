package kh.com.pheaktra.developer.kmp.basic.di.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kh.com.pheaktra.developer.basic.android.network.ApiService
import kh.com.pheaktra.developer.basic.android.network.RetrofitClient
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