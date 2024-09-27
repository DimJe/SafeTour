package com.example.presentation

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.BuildConfig
import com.example.data.datasource.place.PlaceRemoteDataSource
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
import org.junit.Rule
import java.util.Arrays
import java.util.Base64

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

    @Test
    fun useAppContext() = runBlocking {

        val apikey = BuildConfig.GOOGLE_PLACE_API_KEY
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        Places.initializeWithNewPlacesApiEnabled(context,apikey)
        val placeClient = Places.createClient(context)
        
        val dataSource = PlaceRemoteDataSource(placeClient)
        dataSource.getPlace("유럽 관광 명소").collect{
            it.forEach {
                val byteArray = it.name.toByteArray(Charsets.ISO_8859_1)
                val text = String(byteArray,Charsets.UTF_8)
                println("$it / $text")
            }
            //assert(it.isNotEmpty())

        }
    }

}