package com.mehdizadeh.catfeed.presentation.screen.home.viewModel

import com.mehdizadeh.catfeed.core.BaseViewState
import com.mehdizadeh.catfeed.domain.model.CatBreed

data class HomeViewState(
    val isLoading: Boolean = false,
    val catBreeds: List<CatBreed> = emptyList(),
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val page: Int = 0,
    val endReached: Boolean = false

) : BaseViewState