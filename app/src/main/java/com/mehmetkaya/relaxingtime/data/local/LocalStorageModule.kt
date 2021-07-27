package com.mehmetkaya.relaxingtime.data.local

import android.content.SharedPreferences
import com.mehmetkaya.utils.preference.StringPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalStorageModule {

    const val USER_NAME_PREF = "UserNamePref"

    @Provides
    @Singleton
    @Named(USER_NAME_PREF)
    fun provideLoginToken(sharedPreferences: SharedPreferences): StringPreference {
        return StringPreference(sharedPreferences, USER_NAME_PREF)
    }
}
