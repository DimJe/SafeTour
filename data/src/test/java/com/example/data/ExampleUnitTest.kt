package com.example.data

import com.example.data.api.ForeignApi
import com.example.data.api.ForeignXmlApi
import com.example.data.datasource.ForeignRemoteDataSource
import com.example.data.repository.foreign.ForeignInfoRepositoryImpl
import com.google.gson.GsonBuilder
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
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
        .baseUrl("http://apis.data.go.kr")
        .build()
        .create(ForeignApi::class.java)
    val xmlService = Retrofit.Builder()
        .addConverterFactory(TikXmlConverterFactory.create())
        .client(client)
        .baseUrl("http://apis.data.go.kr")
        .build()
        .create(ForeignXmlApi::class.java)
    val foreignInfoRepositoryImpl = ForeignInfoRepositoryImpl(ForeignRemoteDataSource(service, xmlService))
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

}