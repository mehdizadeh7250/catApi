package com.mehdizadeh.catfeed.domain.repository

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    suspend fun saveThemeMode(mode: ThemeMode)
    fun getThemeModeFlow(): Flow<ThemeMode>
}
enum class ThemeMode {
    LIGHT, DARK, SYSTEM
}