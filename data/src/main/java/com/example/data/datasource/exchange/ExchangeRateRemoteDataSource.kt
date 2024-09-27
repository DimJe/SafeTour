package com.example.data.datasource.exchange

import com.example.data.BuildConfig
import com.example.data.api.exchange.ExchangeRateApi
import com.example.data.model.exchange.ExchangeRateData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ExchangeRateRemoteDataSource @Inject constructor(
    private val exchangeRateApi: ExchangeRateApi
) {
    fun getExchangeRate(searchDate: String): Flow<List<ExchangeRateData>> = flow {
        val response = exchangeRateApi.getExchangeRate(BuildConfig.EXCHANGE_RATE_API_KEY,searchDate)
        emit(response)
    }.flowOn(Dispatchers.IO)


}