package com.mehdizadeh.catfeed.data.repository

import com.mehdizadeh.catfeed.core.Result
import com.mehdizadeh.catfeed.di.ApiServices
import com.mehdizadeh.catfeed.domain.mapper.toDomain
import com.mehdizadeh.catfeed.domain.model.CatBreed
import com.mehdizadeh.catfeed.domain.repository.CatBreedRepository
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
        val dtos = apiServices.getBreeds(limit, page)
        val domainList = dtos.map { it.toDomain() }
        emit(Result.Success(domainList))
    }.onStart {
        emit(Result.Loading)
    }.catch { e ->
        emit(Result.Error(message = e.message ?: "Unknown Error", exception = e))
    }
}