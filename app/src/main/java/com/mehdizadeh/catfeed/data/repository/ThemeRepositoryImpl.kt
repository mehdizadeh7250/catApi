package com.mehdizadeh.catfeed.data.repository

import com.mehdizadeh.catfeed.di.dataStore.PreferenceStorage
import com.mehdizadeh.catfeed.di.dataStore.PreferenceStorage.Companion.THEME_KEY
import com.mehdizadeh.catfeed.domain.repository.ThemeMode
import com.mehdizadeh.catfeed.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val preferenceStorage: PreferenceStorage,
) : ThemeRepository {
    override suspend fun saveThemeMode(mode: ThemeMode) {
        preferenceStorage.set(THEME_KEY, mode.name)
    }

    override fun getThemeModeFlow(): Flow<ThemeMode> {
        return preferenceStorage.get<String>(THEME_KEY).map {
            if (it.isNullOrEmpty()) {
                ThemeMode.valueOf(ThemeMode.SYSTEM.name)
            } else {
                ThemeMode.valueOf(it)
            }
        }
    }
}