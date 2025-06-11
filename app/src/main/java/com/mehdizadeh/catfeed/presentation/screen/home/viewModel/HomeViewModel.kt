package com.mehdizadeh.catfeed.presentation.screen.home.viewModel

import androidx.lifecycle.viewModelScope
import com.mehdizadeh.catfeed.core.BaseViewModel
import com.mehdizadeh.catfeed.core.Result
import com.mehdizadeh.catfeed.domain.model.CatParams
import com.mehdizadeh.catfeed.domain.useCase.DeleteFavoriteUseCase
import com.mehdizadeh.catfeed.domain.useCase.GetCatBreedsUseCase
import com.mehdizadeh.catfeed.domain.useCase.PostFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCatBreedsUseCase: GetCatBreedsUseCase,
    private val postFavoriteUseCase: PostFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel<HomeViewActions, HomeViewState, HomeViewEvents>(
    HomeViewState()
) {
    init {
        handle(HomeViewActions.GetBreeds(10,0))
    }

    override fun handle(action: HomeViewActions) {
        when (action) {
            is HomeViewActions.GetBreeds -> {
                handleGetBreeds(action)
            }
            is HomeViewActions.PostFavorite -> {
                handlePostFavorite(action)
            }
            is HomeViewActions.DeleteFavorite -> {
                handleDeleteFavorite(action)
            }
        }
    }
    private fun handlePostFavorite(params: HomeViewActions.PostFavorite) {
        //TODO
    }
    private fun handleDeleteFavorite(params: HomeViewActions.DeleteFavorite) {
        viewModelScope.launch {
            deleteFavoriteUseCase.invoke(Pair(params.id, params.favoriteId)).collectLatest { result->
                setState {
                    it.copy()//TODO
                }
            }

        }
    }
    private fun handleGetBreeds(params: HomeViewActions.GetBreeds) {
        viewModelScope.launch {
            getCatBreedsUseCase.invoke(CatParams(limit = params.limit, page = params.page))
                .collectLatest { result ->
                    when (result) {
                        is Result.Success -> {
                            val isNewPage = params.page > 0
                            setState { state ->
                                state.copy(
                                    catBreeds = if (isNewPage) {
                                        state.catBreeds + result.data
                                    } else {
                                        result.data
                                    },
                                    isLoading = false,
                                    isRefreshing = false,
                                    isLoadingMore = false,
                                    page = params.page,
                                    endReached = result.data.isEmpty()
                                )
                            }
                        }

                        is Result.Error -> {
                            setState {
                                it.copy(
                                    isLoading = false,
                                    isRefreshing = false,
                                    isLoadingMore = false,
                                    error = result.message
                                )
                            }
                        }

                        is Result.Loading -> {
                            setState {
                                it.copy(
                                    isLoading = params.page == 0 && !it.isRefreshing,
                                    isRefreshing = params.page == 0 && it.catBreeds.isNotEmpty(),
                                    isLoadingMore = params.page > 0
                                )
                            }
                        }
                    }
                }
        }
    }

}