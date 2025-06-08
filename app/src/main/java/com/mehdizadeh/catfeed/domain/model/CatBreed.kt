package com.mehdizadeh.catfeed.domain.model

data class CatBreed(
    val id: String,
    val name: String,
    val temperament: String?,
    val origin: String?,
    val description: String?,
    val lifeSpan: String?,
    val adaptability: Int?,
    val affectionLevel: Int?,
    val childFriendly: Int?,
    val dogFriendly: Int?,
    val energyLevel: Int?,
    val grooming: Int?,
    val healthIssues: Int?,
    val intelligence: Int?,
    val sheddingLevel: Int?,
    val socialNeeds: Int?,
    val strangerFriendly: Int?,
    val vocalisation: Int?,
    val experimental: Int?,
    val hairless: Int?,
    val natural: Int?,
    val rare: Int?,
    val rex: Int?,
    val suppressedTail: Int?,
    val shortLegs: Int?,
    val wikipediaUrl: String?,
    val hypoallergenic: Int?,
    val imageUrl: String?,
    val weightImperial: String,
    val weightMetric: String
)
data class CatParams(
    val limit: Int,
    val page : Int
)