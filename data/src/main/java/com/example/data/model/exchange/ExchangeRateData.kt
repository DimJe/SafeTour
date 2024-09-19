package com.example.data.model.exchange

import com.example.domain.entity.exchange.ExchangeRateEntity
import com.google.gson.annotations.SerializedName

data class ExchangeRateData(
    val result: Int,
    @SerializedName("cur_unit") val curUnit: String,
    @SerializedName("cur_nm") val curNm: String,
    val ttb: String,
    val tts: String,
)
fun ExchangeRateData.toDomain() = ExchangeRateEntity(
    result = result,
    curUnit = curUnit,
    curNm = curNm,
    ttb = ttb,
    tts = tts
)
