package com.mehdizadeh.catfeed.data.mapper

import com.mehdizadeh.catfeed.data.model.CatBreedsDto
import com.mehdizadeh.catfeed.data.model.FavoritesDto
import com.mehdizadeh.catfeed.domain.model.CatBreed
import com.mehdizadeh.catfeed.domain.model.Favorite

fun FavoritesDto.toDomain(): Favorite = Favorite(
    imageId = imageId
)