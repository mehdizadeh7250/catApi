package com.mehdizadeh.catfeed.presentation.screen.home.viewModel

import com.mehdizadeh.catfeed.core.BaseViewAction

sealed interface HomeViewActions: BaseViewAction {
    data class GetBreeds(val limit: Int, val page: Int) : HomeViewActions
    data class PostFavorite(val id: String) : HomeViewActions
    data class DeleteFavorite(val id: String,val favoriteId: Int) : HomeViewActions
}