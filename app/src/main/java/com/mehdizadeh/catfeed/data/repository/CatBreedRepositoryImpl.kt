package com.mehdizadeh.catfeed.data.repository

import android.util.Log
import com.mehdizadeh.catfeed.core.Result
import com.mehdizadeh.catfeed.di.ApiServices
import com.mehdizadeh.catfeed.data.mapper.toDomain
import com.mehdizadeh.catfeed.domain.model.CatBreed
import com.mehdizadeh.catfeed.domain.repository.CatBreedRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CatBreedRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : CatBreedRepository {
    override fun getBreeds(
        limit: Int,
        page: Int
    ): Flow<Result<List<CatBreed>>> = flow<Result<List<CatBreed>>> {
        val domainList = coroutineScope {
            val favoritesDeferred = async { apiServices.getFavorites() }
            val breedsDeferred = async { apiServices.getBreeds(limit, page) }
            val breeds = breedsDeferred.await()
            val favorites = favoritesDeferred.await()
            val favoriteImageIds = favorites.map { it.imageId }.toSet()

            // Pass isFavorite to toDomain
            breeds.map { catBreedDto ->
                val domainModel = catBreedDto.toDomain()
                val isFavorite = favoriteImageIds.contains(catBreedDto.referenceImageId)
                domainModel.copy(isFavorite = isFavorite)
            }
        }
        emit(Result.Success(domainList))
    }.onStart {
        emit(Result.Loading)
    }.catch { e ->
        emit(Result.Error(message = e.message ?: "Unknown Error", exception = e))
    }
}