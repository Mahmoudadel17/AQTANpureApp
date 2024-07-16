package com.example.aqtan.di

import android.app.Application
import com.example.aqtan.domain.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {




    @Singleton
    @Provides
    fun provideSharedPreferencesInstance(application: Application): SharedPreferences {
        return SharedPreferences(application)
    }

}