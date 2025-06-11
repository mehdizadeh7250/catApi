package com.mehdizadeh.catfeed.di

import com.mehdizadeh.catfeed.data.model.CatBreedsDto
import com.mehdizadeh.catfeed.data.model.FavoriteActionDto
import com.mehdizadeh.catfeed.data.model.FavoriteActionParams
import com.mehdizadeh.catfeed.data.model.FavoritesDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("v1/breeds")
    suspend fun getBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<CatBreedsDto>

    @GET("v1/favourites")
    suspend fun getFavorites(): List<FavoritesDto>

    @Headers("X-No-Cache: true")
    @POST("v1/favourites")
    suspend fun postFavorite(favoriteActionParams: FavoriteActionParams): FavoriteActionDto

    @Headers("X-No-Cache: true")
    @DELETE("v1/favourites")
    suspend fun deleteFavorite(@Path("favourite_id") favoriteId: Int): FavoriteActionDto
}