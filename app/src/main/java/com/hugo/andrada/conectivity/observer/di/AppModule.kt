package com.hugo.andrada.conectivity.observer.di

import android.content.Context
import com.hugo.andrada.conectivity.observer.util.ConnectivityObserver
import com.hugo.andrada.conectivity.observer.util.ConnectivityObserverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityObserver {
        return ConnectivityObserverImpl(context)
    }
}