package com.mehdizadeh.catfeed.domain.useCase

import com.mehdizadeh.catfeed.core.BaseUseCase
import com.mehdizadeh.catfeed.core.Result
import com.mehdizadeh.catfeed.data.model.FavoriteActionParams
import com.mehdizadeh.catfeed.domain.model.FavoriteAction
import com.mehdizadeh.catfeed.domain.repository.FavoriteActionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostFavoriteUseCase @Inject constructor(
    private val favoriteActionRepository: FavoriteActionRepository
) : BaseUseCase<FavoriteActionParams, FavoriteAction>() {
    override suspend fun execute(params: FavoriteActionParams): Flow<Result<FavoriteAction>> {
       return favoriteActionRepository.postFavorite(params)
    }
}