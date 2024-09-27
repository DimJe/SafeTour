package com.example.data.repository.exchange

import com.example.data.datasource.exchange.ExchangeRateRemoteDataSource
import com.example.data.model.exchange.toDomain
import com.example.domain.entity.exchange.ExchangeRateEntity
import com.example.domain.repository.exchange.ExchangeRateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    private val exchangeRateRemoteDataSource: ExchangeRateRemoteDataSource
): ExchangeRateRepository {
    override fun getExchangeRate(
        searchDate: String
    ): Flow<Result<List<ExchangeRateEntity>>> = flow {
        try {
            val response = exchangeRateRemoteDataSource.getExchangeRate(searchDate).first()
            val exchangeRate = response.map { it.toDomain() }
            emit(Result.success(exchangeRate))
        }catch (e: Exception){
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

}