package com.mehmetkaya.utils.preference

import android.content.SharedPreferences
import javax.inject.Inject

class LongPreference @Inject constructor(private val preferences: SharedPreferences, private val key: String) {

    val isSet = preferences.contains(key)

    fun get() = preferences.getLong(key, 0)

    fun set(value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun delete() {
        preferences.edit().remove(key).apply()
    }
}
