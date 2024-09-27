package com.example.presentation.di

import com.example.data.api.exchange.ExchangeRateApi
import com.example.data.api.foreign.ForeignApi
import com.example.data.api.foreign.ForeignXmlApi
import com.example.data.api.naver.NaverApi
import com.google.gson.GsonBuilder
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideForeignApi(client: OkHttpClient): ForeignApi {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .baseUrl("http://apis.data.go.kr/")
            .build()
            .create(ForeignApi::class.java)
    }

    @Provides
    @Singleton
    fun provideForeignXmlApi(client: OkHttpClient): ForeignXmlApi {
        return Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create())
            .client(client)
            .baseUrl("http://apis.data.go.kr/")
            .build()
            .create(ForeignXmlApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangeRareApi(client: OkHttpClient): ExchangeRateApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .baseUrl("https://www.koreaexim.go.kr/site/program/financial/")
            .build()
            .create(ExchangeRateApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNaverApi(client: OkHttpClient): NaverApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .baseUrl("https://openapi.naver.com/v1/search/")
            .build()
            .create(NaverApi::class.java)
    }

}