package com.mehdizadeh.catfeed.domain.useCase

import com.mehdizadeh.catfeed.core.BaseUseCase
import com.mehdizadeh.catfeed.core.Result
import com.mehdizadeh.catfeed.domain.model.FavoriteAction
import com.mehdizadeh.catfeed.domain.repository.FavoriteActionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoriteActionRepository: FavoriteActionRepository
) : BaseUseCase<Pair<String, Int>, FavoriteAction>() {
    override suspend fun execute(params: Pair<String, Int>): Flow<Result<FavoriteAction>> {
        return favoriteActionRepository.deleteFavorite(params.first, params.second)
    }
}