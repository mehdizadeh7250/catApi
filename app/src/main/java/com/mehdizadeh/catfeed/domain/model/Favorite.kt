package com.mehdizadeh.catfeed.domain.model

data class Favorite(
    val imageId: String = ""
)
data class FavoriteAction(
    val isSuccessful: Boolean = false,
    val favoriteId:Int = 0,
    val message: String
)
