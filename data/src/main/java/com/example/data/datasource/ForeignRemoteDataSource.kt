package com.example.data.datasource

import com.example.data.api.ForeignApi
import com.example.data.api.ForeignXmlApi
import com.example.data.model.BasicData
import com.example.data.model.ContactData
import com.example.data.model.EntryData
import com.example.data.model.PoliceData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class ForeignRemoteDataSource(private val foreignApi: ForeignApi,private val foreignXmlApi: ForeignXmlApi) {

    fun getContact(serviceKey: String, countryName: String): Flow<ContactData> = flow{
        val response = foreignApi.getContact(serviceKey, countryName = countryName)

        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getPolice(serviceKey: String, countryName: String): Flow<PoliceData> = flow{
        val response = foreignApi.getPolice(serviceKey, countryName = countryName)
        emit(response)

    }.flowOn(Dispatchers.IO)

    fun getBasic(serviceKey: String, countryName: String): Flow<Response<BasicData>> = flow{
        val response = foreignXmlApi.getBasic(serviceKey, countryName = countryName)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getEntryCondition(serviceKey: String, countryName: String): Flow<EntryData> = flow{
        val response = foreignApi.getEntryCondition(serviceKey, countryName = countryName)
        emit(response)
    }.flowOn(Dispatchers.IO)
}