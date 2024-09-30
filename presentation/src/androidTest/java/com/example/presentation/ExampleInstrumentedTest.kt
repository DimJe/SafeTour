package com.example.presentation

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.BuildConfig
import com.example.data.datasource.place.PlaceRemoteDataSource
import com.example.domain.usecase.exchange.GetExchangeRateUseCase
import com.example.domain.usecase.foreign.GetBasicInfoUseCase
import com.example.domain.usecase.foreign.GetContactUseCase
import com.example.domain.usecase.foreign.GetEntryConditionUseCase
import com.example.domain.usecase.foreign.GetPoliceUseCase
import com.example.domain.usecase.naver.GetBlogUseCase
import com.example.domain.usecase.naver.GetImageUseCase
import com.example.domain.usecase.place.GetPlaceUseCase
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.SearchByTextRequest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.util.Arrays
import java.util.Base64
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getExchangeRateUseCase: GetExchangeRateUseCase
    @Inject
    lateinit var getBasicInfoUseCase: GetBasicInfoUseCase
    @Inject
    lateinit var getContactUseCase: GetContactUseCase
    @Inject
    lateinit var getEntryConditionUseCase: GetEntryConditionUseCase
    @Inject
    lateinit var getPoliceUseCase: GetPoliceUseCase
    @Inject
    lateinit var getBlogUseCase: GetBlogUseCase
    @Inject
    lateinit var getImageUseCase: GetImageUseCase
    @Inject
    lateinit var getPlaceUseCase: GetPlaceUseCase

    @Before
    fun set(){
        hiltRule.inject()
    }
    @Test
    fun imageTest() = runBlocking {

        getImageUseCase("일본").collect{ result ->
            result.fold(
                onSuccess = {
//                    it.items.forEach {
//                        println(it.link)
//                    }
                    assertEquals(it.items.size,20)
                },
                onFailure = {
                    println(it.message)
                }
            )
        }
    }
    @Test
    fun placeTest() = runBlocking {

        getPlaceUseCase("일본").collect{ result ->
            result.fold(
                onSuccess = {
                    assertEquals(it.isNotEmpty(),true)
                },
                onFailure = {
                    println(it.message)
                }
            )
        }
    }
    @Test
    fun blogTest() = runBlocking {
        getBlogUseCase("일본").collect{ result ->
            result.fold(
                onSuccess = {
                    assertEquals(it.items.isNotEmpty(),true)
                },
                onFailure = {
                    println(it.message)
                }
            )
        }
    }
    @Test
    fun policeTest() = runBlocking {
        getPoliceUseCase("일본").collect{ result->
            result.fold(
                onSuccess = {
                    assertEquals(it.data[0].countryNm,"일본")
                },
                onFailure = {
                    println(it.message)
                }
            )
        }
    }
    @Test
    fun entryConditionTest() = runBlocking {
        getEntryConditionUseCase("일본").collect{ result ->
            result.fold(
                onSuccess = {
                    assertEquals(it.data.first().countryNm,"일본")
                },
                onFailure = {
                    println(it.message)
                }
            )
        }
    }
    @Test
    fun contactTest() = runBlocking {
        getContactUseCase("일본").collect{ result ->
            result.fold(
                onSuccess = {
                    assertEquals(it.data.first().countryNm,"일본")
                },
                onFailure = {
                    println(it.message)
                }
            )
        }
    }
    @Test
    fun basicInfoTest() = runBlocking {
        getBasicInfoUseCase("일본").collect{ result ->
            result.fold(
                onSuccess = {
                    assertEquals(it.countryName,"일본")
                },
                onFailure = {
                    println(it.message)
                }
            )
        }
    }
    @Test
    fun exchangeTest() = runBlocking {
        getExchangeRateUseCase("20240930").collect{ result ->
            result.fold(
                onSuccess = {
                    assertEquals(it.first().result,1)
                },
                onFailure = {

                }
            )
        }
    }


}