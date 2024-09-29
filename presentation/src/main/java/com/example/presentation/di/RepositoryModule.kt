package com.example.presentation.di

import com.example.data.api.foreign.ForeignApi
import com.example.data.api.foreign.ForeignXmlApi
import com.example.data.datasource.exchange.ExchangeRateRemoteDataSource
import com.example.data.datasource.foreign.ForeignRemoteDataSource
import com.example.data.datasource.naver.NaverRemoteDataSource
import com.example.data.datasource.place.PlaceRemoteDataSource
import com.example.data.repository.exchange.ExchangeRateRepositoryImpl
import com.example.data.repository.foreign.ForeignInfoRepositoryImpl
import com.example.data.repository.naver.NaverRepositoryImpl
import com.example.data.repository.place.PlaceRepositoryImpl
import com.example.domain.repository.exchange.ExchangeRateRepository
import com.example.domain.repository.foreign.ForeignInfoRepository
import com.example.domain.repository.naver.NaverRepository
import com.example.domain.repository.place.PlaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideForeignRepository(foreignRemoteDataSource: ForeignRemoteDataSource): ForeignInfoRepository =
        ForeignInfoRepositoryImpl(foreignRemoteDataSource)

    @Provides
    @Singleton
    fun provideExchangeRateRepository(exchangeRateRemoteDataSource: ExchangeRateRemoteDataSource): ExchangeRateRepository =
        ExchangeRateRepositoryImpl(exchangeRateRemoteDataSource)

    @Provides
    @Singleton
    fun provideNaverRepository(naverRemoteDataSource: NaverRemoteDataSource): NaverRepository =
        NaverRepositoryImpl(naverRemoteDataSource)

    @Provides
    @Singleton
    fun providePlaceRepository(placeRemoteDataSource: PlaceRemoteDataSource): PlaceRepository =
        PlaceRepositoryImpl(placeRemoteDataSource)

}