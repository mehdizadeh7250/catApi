package com.mehdizadeh.catfeed.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteActionDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: String
) : Parcelable


data class FavoriteActionParams(
    val imageId: String,
    val subId: String = "my-user-1234"
)



