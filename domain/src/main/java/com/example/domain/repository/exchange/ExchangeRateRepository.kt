package com.example.domain.repository.exchange

import com.example.domain.entity.exchange.ExchangeRateEntity
import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {

    fun getExchangeRate(authKey: String, searchDate: String): Flow<Result<List<ExchangeRateEntity>>>
}