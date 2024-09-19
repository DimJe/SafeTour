package com.example.domain.entity.exchange

data class ExchangeRateEntity(
    val result: Int,
    val curUnit: String,
    val curNm: String,
    val ttb: String,
    val tts: String,
)
