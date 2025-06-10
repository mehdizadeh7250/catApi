package com.mehdizadeh.catfeed.data.mapper

import com.mehdizadeh.catfeed.data.model.CatBreedsDto
import com.mehdizadeh.catfeed.domain.model.CatBreed

fun CatBreedsDto.toDomain(): CatBreed = CatBreed(
    id = id,
    name = name,
    temperament = temperament,
    origin = origin,
    description = description,
    lifeSpan = lifeSpan,
    adaptability = adaptability,
    affectionLevel = affectionLevel,
    childFriendly = childFriendly,
    dogFriendly = dogFriendly,
    energyLevel = energyLevel,
    grooming = grooming,
    healthIssues = healthIssues,
    intelligence = intelligence,
    sheddingLevel = sheddingLevel,
    socialNeeds = socialNeeds,
    strangerFriendly = strangerFriendly,
    vocalisation = vocalisation,
    experimental = experimental,
    hairless = hairless,
    natural = natural,
    rare = rare,
    rex = rex,
    suppressedTail = suppressedTail,
    shortLegs = shortLegs,
    wikipediaUrl = wikipediaUrl,
    hypoallergenic = hypoallergenic,
    imageUrl = image?.url,
    weightImperial = weight.imperial,
    referenceImageId = referenceImageId,
    weightMetric = weight.metric,
)
