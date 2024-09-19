package com.example.data.api.exchange

import com.example.data.model.exchange.ExchangeRateData
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateApi {

    @GET("exchangeJSON")
    suspend fun getExchangeRate(
        @Query("authkey") authKey: String,
        @Query("searchdate") searchDate: String,
        @Query("data") data: String = "AP01"
    ): List<ExchangeRateData>
}