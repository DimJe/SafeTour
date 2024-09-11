package com.example.data.model

import com.example.domain.entity.foreign.Police
import com.example.domain.entity.foreign.PoliceEntity
import com.google.gson.annotations.SerializedName

data class PoliceData(
    val data: List<PoliceDetail>
)
data class PoliceDetail(
    @SerializedName("current_travel_alarm") val currentTravelAlarm: String,
    @SerializedName("country_nm") val countryNm: String
)
fun PoliceDetail.toDomain() = Police(
    currentTravelAlarm = currentTravelAlarm,
    countryNm = countryNm
)
fun PoliceData.toDomain() = PoliceEntity(
    data = data.map { it.toDomain() }
)
