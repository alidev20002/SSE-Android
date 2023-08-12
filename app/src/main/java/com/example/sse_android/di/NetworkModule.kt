package com.example.sse_android.di

import com.example.sse_android.data.network.SSEService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideSSEService(): SSEService {
        return SSEService()
    }
}