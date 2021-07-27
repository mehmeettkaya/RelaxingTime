package com.mehmetkaya.utils.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringPreference @JvmOverloads constructor(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defaultValue: String = ""
) : ReadWriteProperty<Any, String> {

    val isSet: Boolean
        get() = preferences.contains(key)

    fun get(): String {
        return preferences.getString(key, defaultValue)!!
    }

    fun set(value: String, commit: Boolean = false) {
        preferences.edit(commit) {
            putString(key, value)
        }
    }

    fun delete() {
        preferences.edit {
            remove(key)
        }
    }

    override fun getValue(thisRef: Any, property: KProperty<*>) = get()

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) = set(value)
}
