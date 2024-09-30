package com.example.data.datasource.place

import com.example.data.model.place.PlaceData
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.SearchByTextRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class PlaceRemoteDataSource @Inject constructor(
    private val placeClient: PlacesClient
) {

    fun getPlace(query: String): Flow<List<PlaceData>> = callbackFlow {

        val placeFields = listOf(Place.Field.ID, Place.Field.NAME)
        val searchRequest =
            SearchByTextRequest.builder("$query 관광 명소", placeFields).setRegionCode("kr")
                .build()

        placeClient.searchByText(searchRequest).addOnSuccessListener{
            trySend(it.places.map { place ->
                PlaceData(
                    id = place.id!!,
                    name = place.name!!)
            })
            close()
        }
        awaitClose()


    }
}