package com.mehdizadeh.catfeed.domain.useCase

import com.mehdizadeh.catfeed.domain.repository.ThemeMode
import com.mehdizadeh.catfeed.domain.repository.ThemeRepository
import javax.inject.Inject

class SaveThemeModeUseCase @Inject constructor(private val repo: ThemeRepository) {
    suspend operator fun invoke(mode: ThemeMode) = repo.saveThemeMode(mode)
}