package com.example.presentation.di

import com.example.domain.repository.exchange.ExchangeRateRepository
import com.example.domain.repository.foreign.ForeignInfoRepository
import com.example.domain.repository.naver.NaverRepository
import com.example.domain.repository.place.PlaceRepository
import com.example.domain.usecase.exchange.GetExchangeRateUseCase
import com.example.domain.usecase.foreign.GetBasicInfoUseCase
import com.example.domain.usecase.foreign.GetContactUseCase
import com.example.domain.usecase.foreign.GetEntryConditionUseCase
import com.example.domain.usecase.foreign.GetPoliceUseCase
import com.example.domain.usecase.naver.GetBlogUseCase
import com.example.domain.usecase.naver.GetImageUseCase
import com.example.domain.usecase.place.GetPlaceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providePlaceUseCase(placeRepository: PlaceRepository): GetPlaceUseCase =
        GetPlaceUseCase(placeRepository)

    @Provides
    @Singleton
    fun provideImageUseCase(naverRepository: NaverRepository): GetImageUseCase =
        GetImageUseCase(naverRepository)

    @Provides
    @Singleton
    fun provideBlogUseCase(naverRepository: NaverRepository): GetBlogUseCase =
        GetBlogUseCase(naverRepository)

    @Provides
    @Singleton
    fun providePoliceUseCase(foreignInfoRepository: ForeignInfoRepository): GetPoliceUseCase =
        GetPoliceUseCase(foreignInfoRepository)

    @Provides
    @Singleton
    fun provideEntryConditionUseCase(foreignInfoRepository: ForeignInfoRepository): GetEntryConditionUseCase =
        GetEntryConditionUseCase(foreignInfoRepository)

    @Provides
    @Singleton
    fun provideBasicInfoUseCase(foreignInfoRepository: ForeignInfoRepository): GetBasicInfoUseCase =
        GetBasicInfoUseCase(foreignInfoRepository)

    @Provides
    @Singleton
    fun provideContactUseCase(foreignInfoRepository: ForeignInfoRepository): GetContactUseCase =
        GetContactUseCase(foreignInfoRepository)

    @Provides
    @Singleton
    fun provideExchangeUseCase(exchangeRateRepository: ExchangeRateRepository): GetExchangeRateUseCase =
        GetExchangeRateUseCase(exchangeRateRepository)


}