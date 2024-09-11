package com.example.data

import androidx.test.ext.junit.runners.AndroidJUnit4
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
import org.junit.runner.RunWith

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

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
}