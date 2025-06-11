package com.mehdizadeh.catfeed.data.repository

import com.mehdizadeh.catfeed.core.Result
import com.mehdizadeh.catfeed.data.local.dao.CatDao
import com.mehdizadeh.catfeed.data.mapper.toDomain
import com.mehdizadeh.catfeed.data.mapper.toEntity
import com.mehdizadeh.catfeed.data.model.FavoriteActionParams
import com.mehdizadeh.catfeed.di.ApiServices
import com.mehdizadeh.catfeed.domain.model.FavoriteAction
import com.mehdizadeh.catfeed.domain.repository.FavoriteActionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class FavoriteActionRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val catDao: CatDao
) : FavoriteActionRepository {
    override suspend fun postFavorite(favoriteActionParams: FavoriteActionParams): Flow<Result<FavoriteAction>> =
        flow<Result<FavoriteAction>> {
            val response = apiServices.postFavorite(favoriteActionParams)
            val res = response.toDomain(true)
            val cat = response.toEntity(favoriteActionParams.imageId)
            catDao.update(cat)
            emit(Result.Success(res))
        }.onStart {
            val cat = favoriteActionParams.toEntity()
            catDao.insert(cat)
            emit(Result.Loading)
        }.catch { e ->
            emit(Result.Error(message = e.message ?: "Unknown Error", exception = e))
        }

    override suspend fun deleteFavorite(catId:String,favoriteId: Int): Flow<Result<FavoriteAction>> =
        flow<Result<FavoriteAction>> {
            val response = apiServices.deleteFavorite(favoriteId)
            val res = response.toDomain(true)
            emit(Result.Success(res))
        }.onStart {
            catDao.deleteByCatId(catId)
            emit(Result.Loading)
        }.catch { e ->
            emit(Result.Error(message = e.message ?: "Unknown Error", exception = e))
        }
}