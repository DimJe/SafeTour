package com.example.domain.usecase.place

import com.example.domain.repository.place.PlaceRepository

class GetPlaceUseCase(
    private val placeRepository: PlaceRepository
) {
    operator fun invoke(name: String) = placeRepository.getPlace(name)
}