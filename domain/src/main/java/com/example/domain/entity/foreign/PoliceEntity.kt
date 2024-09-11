package com.example.domain.entity.foreign

data class PoliceEntity(
    val data: List<Police>,
)
data class Police(
    val currentTravelAlarm: String,
    val countryNm: String

)