package com.example.presentation.di

import android.app.Application
import android.content.Context
import com.example.data.BuildConfig
import com.example.data.api.exchange.ExchangeRateApi
import com.example.data.api.foreign.ForeignApi
import com.example.data.api.foreign.ForeignXmlApi
import com.example.data.api.naver.NaverApi
import com.example.data.datasource.exchange.ExchangeRateRemoteDataSource
import com.example.data.datasource.foreign.ForeignRemoteDataSource
import com.example.data.datasource.naver.NaverRemoteDataSource
import com.example.data.datasource.place.PlaceRemoteDataSource
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun providePlaceClient(@ApplicationContext context: Context): PlacesClient {
        BuildConfig.GOOGLE_PLACE_API_KEY
        Places.initializeWithNewPlacesApiEnabled(context,BuildConfig.GOOGLE_PLACE_API_KEY)
        return Places.createClient(context)
    }

    @Provides
    @Singleton
    fun provideExchangeDataSource(exchangeRateApi: ExchangeRateApi): ExchangeRateRemoteDataSource {
        return ExchangeRateRemoteDataSource(exchangeRateApi)
    }

    @Provides
    @Singleton
    fun provideForeignDataSource(foreignXmlApi: ForeignXmlApi,foreignApi: ForeignApi): ForeignRemoteDataSource {
        return ForeignRemoteDataSource(foreignApi, foreignXmlApi)
    }

    @Provides
    @Singleton
    fun provideNaverDataSource(naverApi: NaverApi): NaverRemoteDataSource {
        return NaverRemoteDataSource(naverApi)
    }

    @Provides
    @Singleton
    fun providePlaceDataSource(placeClient: PlacesClient): PlaceRemoteDataSource {
        return PlaceRemoteDataSource(placeClient)
    }
}