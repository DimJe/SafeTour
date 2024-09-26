package com.example.data

import com.example.data.api.naver.NaverApi
import com.example.data.api.exchange.ExchangeRateApi
import com.example.data.api.foreign.ForeignApi
import com.example.data.api.foreign.ForeignXmlApi
import com.example.data.datasource.naver.NaverRemoteDataSource
import com.example.data.datasource.exchange.ExchangeRateRemoteDataSource
import com.example.data.datasource.foreign.ForeignRemoteDataSource
import com.example.data.repository.naver.NaverRepositoryImpl
import com.example.data.repository.exchange.ExchangeRateRepositoryImpl
import com.example.data.repository.foreign.ForeignInfoRepositoryImpl
import com.google.gson.GsonBuilder
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
    val gson = GsonBuilder().setLenient().create()
    val service = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .baseUrl("http://apis.data.go.kr/")
        .build()
        .create(ForeignApi::class.java)
    val xmlService = Retrofit.Builder()
        .addConverterFactory(TikXmlConverterFactory.create())
        .client(client)
        .baseUrl("http://apis.data.go.kr/")
        .build()
        .create(ForeignXmlApi::class.java)
    val foreignInfoRepositoryImpl = ForeignInfoRepositoryImpl(ForeignRemoteDataSource(service, xmlService))

    val exchangeRateService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .baseUrl("https://www.koreaexim.go.kr/site/program/financial/")
        .build()
        .create(ExchangeRateApi::class.java)
    val exchangeRateRepositoryImpl = ExchangeRateRepositoryImpl(ExchangeRateRemoteDataSource(exchangeRateService))

    val blogService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .baseUrl("https://openapi.naver.com/v1/search/")
        .build()
        .create(NaverApi::class.java)
    val naverRepositoryImpl = NaverRepositoryImpl(NaverRemoteDataSource(blogService))

    @Test
    //평일 11시 이전이면 주말 공휴일 제외 가까운 과거의 평일을 호출해야함
    fun 환율테스트() = runBlocking {
        exchangeRateRepositoryImpl.getExchangeRate(BuildConfig.EXCHANGE_RATE_API_KEY, "20240913")
            .collect{ result ->
                result.fold(
                    onSuccess = { assert(it.filter { it.curNm == "미국 달러"}.isNotEmpty()) },
                    onFailure = { println(it.message) }
                )
            }
    }

    @Test
    fun 입국조건() = runBlocking {

        foreignInfoRepositoryImpl.getEntryCondition(BuildConfig.DATA_POTAL_API_KEY, "일본")
            .collect{ result ->
                result.fold(
                    onSuccess = { assert(it.data.first().countryNm == "일본") },
                    onFailure = { println(it.message) }
                )
            }

    }
    @Test
    fun 현지연락처() = runBlocking {

        foreignInfoRepositoryImpl.getContact(BuildConfig.DATA_POTAL_API_KEY, "일본")
            .collect{ result ->
                result.fold(
                    onSuccess = { assert(it.data.first().countryNm == "일본") },
                    onFailure = { println(it.message) }
                )
            }

    }
    @Test
    fun 기본정보() = runBlocking {
        foreignInfoRepositoryImpl.getBasicInfo(BuildConfig.DATA_POTAL_API_KEY, "일본")
            .collect{ result ->
                result.fold(
                    onSuccess = { assert(it.countryName == "일본")
                                println(it.basic)
                    },
                    onFailure = { println(it.message) }
                )
            }

    }
    @Test
    fun 치안상황() = runBlocking {
        foreignInfoRepositoryImpl.getPolice(BuildConfig.DATA_POTAL_API_KEY, "일본")
            .collect{ result ->
                result.fold(
                    onSuccess = { assert(it.data.first().countryNm == "일본") },
                    onFailure = { println(it.message) }
                )
            }
    }
    @Test
    fun getBlog() = runBlocking {
        naverRepositoryImpl.getBlog("일본 여행지 추천")
            .collect { result ->
                result.fold(
                    onSuccess = { it.items.forEach { println(it) } },
                    onFailure = { println(it.message) }
                )
            }
    }
    @Test
    fun getImage() = runBlocking {
        naverRepositoryImpl.getImage("일본 관광지 사진")
            .collect{ result ->
                result.fold(
                    onSuccess = {it.items.forEach { println(it) }},
                    onFailure = { println(it.message) }
                )
            }
    }


}