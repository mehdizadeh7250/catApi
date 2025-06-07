package com.mehdizadeh.catfeed.domain.mapper

import com.mehdizadeh.catfeed.data.local.entity.CatEntity

fun CatEntity.toDomain() = Note(id = id, catName = catName)
fun Note.toEntity() = CatEntity(id = id, catName = content)