package com.mehdizadeh.catfeed.presentation.screen.home.viewModel

import com.mehdizadeh.catfeed.core.BaseViewState
import com.mehdizadeh.catfeed.domain.model.CatBreed

data class HomeViewState(
    val isLoading: Boolean = false,
    val catBreeds: List<CatBreed> = emptyList(),
    val error: String? = null

) : BaseViewState