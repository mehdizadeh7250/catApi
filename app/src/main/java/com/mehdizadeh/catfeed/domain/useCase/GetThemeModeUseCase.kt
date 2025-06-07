package com.mehdizadeh.catfeed.domain.useCase

import com.mehdizadeh.catfeed.domain.repository.ThemeMode
import com.mehdizadeh.catfeed.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeModeUseCase @Inject constructor(private val repo: ThemeRepository) {
    operator fun invoke(): Flow<ThemeMode> = repo.getThemeModeFlow()
}