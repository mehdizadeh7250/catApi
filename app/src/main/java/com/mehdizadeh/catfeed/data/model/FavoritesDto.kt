package com.mehdizadeh.catfeed.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoritesDto(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Image,
    @SerializedName("image_id")
    val imageId: String,
    @SerializedName("sub_id")
    val subId: String,
    @SerializedName("user_id")
    val userId: String
) : Parcelable

