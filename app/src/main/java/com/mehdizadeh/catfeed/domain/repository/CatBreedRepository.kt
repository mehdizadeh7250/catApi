package com.mehdizadeh.catfeed.domain.repository

import com.mehdizadeh.catfeed.core.Result
import com.mehdizadeh.catfeed.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatBreedRepository {
    fun getBreeds(limit: Int, page: Int): Flow<Result<List<CatBreed>>>
}