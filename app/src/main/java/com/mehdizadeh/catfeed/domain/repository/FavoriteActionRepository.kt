package com.mehdizadeh.catfeed.domain.repository

import com.mehdizadeh.catfeed.core.Result
import com.mehdizadeh.catfeed.data.model.FavoriteActionDto
import com.mehdizadeh.catfeed.data.model.FavoriteActionParams
import com.mehdizadeh.catfeed.domain.model.FavoriteAction
import kotlinx.coroutines.flow.Flow

interface FavoriteActionRepository {
    suspend fun postFavorite(favoriteActionParams: FavoriteActionParams): Flow<Result<FavoriteAction>>
    suspend fun deleteFavorite(catId:String,favoriteId: Int): Flow<Result<FavoriteAction>>

}