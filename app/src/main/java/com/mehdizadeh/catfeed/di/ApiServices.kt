package com.mehdizadeh.catfeed.di

import com.mehdizadeh.catfeed.data.model.CatBreedsDto
import com.mehdizadeh.catfeed.data.model.FavoritesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("v1/breeds")
    suspend fun getBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<CatBreedsDto>

    @GET("v1/favourites")
    suspend fun getFavorites(): List<FavoritesDto>
}