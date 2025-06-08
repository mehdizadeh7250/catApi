package com.mehdizadeh.catfeed.domain.useCase

import com.mehdizadeh.catfeed.core.BaseUseCase
import com.mehdizadeh.catfeed.core.Result
import com.mehdizadeh.catfeed.domain.model.CatBreed
import com.mehdizadeh.catfeed.domain.model.CatParams
import com.mehdizadeh.catfeed.domain.repository.CatBreedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatBreedsUseCase @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) : BaseUseCase<CatParams, List<CatBreed>>() {
    override suspend fun execute(params: CatParams): Flow<Result<List<CatBreed>>> {
        return catBreedRepository.getBreeds(params.limit, params.page)
    }

}