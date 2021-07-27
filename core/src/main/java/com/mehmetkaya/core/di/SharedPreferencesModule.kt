package com.mehmetkaya.core.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SharedPreferencesModule {

    private const val SHARED_PREFERENCES = "SharedPreferences"

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE)
    }
}
