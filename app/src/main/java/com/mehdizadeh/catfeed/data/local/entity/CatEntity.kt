package com.mehdizadeh.catfeed.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey()
    val catId: String,
    val favoriteId : Int? = null

)