package com.mehdizadeh.catfeed.data.mapper

import com.mehdizadeh.catfeed.data.local.entity.CatEntity
import com.mehdizadeh.catfeed.data.model.FavoriteActionDto
import com.mehdizadeh.catfeed.data.model.FavoriteActionParams
import com.mehdizadeh.catfeed.data.model.FavoritesDto
import com.mehdizadeh.catfeed.domain.model.Favorite
import com.mehdizadeh.catfeed.domain.model.FavoriteAction

fun FavoritesDto.toDomain(): Favorite = Favorite(
    imageId = imageId
)
fun CatEntity.toDomain(): Favorite = Favorite(
    imageId = catId
)
fun FavoriteActionDto.toDomain(isSuccessful: Boolean): FavoriteAction = FavoriteAction(
    isSuccessful = isSuccessful,
    favoriteId = id,
    message = message
)
fun FavoriteActionDto.toEntity(catId: String): CatEntity = CatEntity(
    favoriteId = id,
    catId = catId,
)
fun FavoriteActionParams.toEntity(): CatEntity = CatEntity(
    catId = imageId,
    favoriteId = 0

)