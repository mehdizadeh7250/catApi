package com.mehdizadeh.catfeed.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatBreed(
    val id: String = "",
    val name: String = "",
    val temperament: String? = "",
    val origin: String? = "",
    val description: String? = "",
    val lifeSpan: String? = "",
    val adaptability: Int?? = 0,
    val affectionLevel: Int?? = 0,
    val childFriendly: Int?? = 0,
    val dogFriendly: Int?? = 0,
    val energyLevel: Int?? = 0,
    val grooming: Int?? = 0,
    val healthIssues: Int?? = 0,
    val intelligence: Int?? = 0,
    val sheddingLevel: Int?? = 0,
    val socialNeeds: Int?? = 0,
    val strangerFriendly: Int?? = 0,
    val vocalisation: Int?? = 0,
    val experimental: Int?? = 0,
    val hairless: Int?? = 0,
    val natural: Int?? = 0,
    val rare: Int?? = 0,
    val rex: Int?? = 0,
    val suppressedTail: Int?? = 0,
    val shortLegs: Int?? = 0,
    val wikipediaUrl: String? = "",
    val hypoallergenic: Int? = 0,
    val imageUrl: String? = "",
    val weightImperial: String = "",
    val weightMetric: String = "",
    val isFavorite : Boolean = false,
    val referenceImageId: String = ""
): Parcelable

data class CatParams(
    val limit: Int,
    val page: Int
)