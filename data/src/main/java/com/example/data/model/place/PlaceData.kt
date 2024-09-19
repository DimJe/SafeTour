package com.example.data.model.place

import com.example.domain.entity.place.PlaceEntity

data class PlaceData(
    val id: String,
    val name: String
)
fun PlaceData.toDomain() = PlaceEntity(
    id = id,
    name = name
)