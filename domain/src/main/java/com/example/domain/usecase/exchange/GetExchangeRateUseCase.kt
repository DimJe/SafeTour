package com.example.domain.usecase.exchange

import com.example.domain.repository.exchange.ExchangeRateRepository

class GetExchangeRateUseCase(
    private val exchangeRateRepository: ExchangeRateRepository
) {
    operator fun invoke(authKey: String, searchDate: String) = exchangeRateRepository.getExchangeRate(authKey, searchDate)
}