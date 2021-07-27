package com.mehmetkaya.utils.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class IntPreference @JvmOverloads constructor(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defaultValue: Int = 0
) : ReadWriteProperty<Any, Int> {

    fun get(): Int {
        return preferences.getInt(key, defaultValue)
    }

    val isSet: Boolean
        get() = preferences.contains(key)

    fun set(value: Int) = preferences.edit {
        putInt(key, value)
    }

    fun delete() = preferences.edit {
        remove(key)
    }

    override fun getValue(thisRef: Any, property: KProperty<*>) = get()

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) = set(value)
}
