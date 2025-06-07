package com.mehdizadeh.catfeed.di.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceStorage @Inject constructor(
     val dataStore: DataStore<Preferences>
) {
    companion object {
        const val THEME_KEY = "THEME_MODE"
    }
    suspend inline fun <reified T> set(key: String, value: T) {
        dataStore.edit { preferences ->
            when (T::class) {
                Boolean::class -> {
                    val dataStoreKey = booleanPreferencesKey(key)
                    preferences[dataStoreKey] = value as Boolean
                }
                String::class -> {
                    val dataStoreKey = stringPreferencesKey(key)
                    preferences[dataStoreKey] = value as String
                }
                Int::class -> {
                    val dataStoreKey = intPreferencesKey(key)
                    preferences[dataStoreKey] = value as Int
                }
                Long::class -> {
                    val dataStoreKey = longPreferencesKey(key)
                    preferences[dataStoreKey] = value as Long
                }
                Double::class -> {
                    val dataStoreKey = doublePreferencesKey(key)
                    preferences[dataStoreKey] = value as Double
                }
                else -> error("Unsupported type ${T::class}")
            }
        }
    }

    inline fun <reified T> get(key: String): Flow<T?> {
        val dataStoreKey = when (T::class) {
            Boolean::class -> booleanPreferencesKey(key)
            String::class -> stringPreferencesKey(key)
            Int::class -> intPreferencesKey(key)
            Long::class -> longPreferencesKey(key)
            Double::class -> doublePreferencesKey(key)
            else -> error("Unsupported type ${T::class}")
        }
        return dataStore.data.map { preferences -> preferences[dataStoreKey] as T? }
    }

    suspend fun remove(key: String) {
        dataStore.edit { it.remove(stringPreferencesKey(key)) }
    }

    suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}
