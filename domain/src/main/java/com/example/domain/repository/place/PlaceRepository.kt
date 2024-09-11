package com.example.domain.repository.place

import com.example.domain.entity.place.PlaceEntity
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getPlace(name: String): Flow<Result<PlaceEntity>>
}