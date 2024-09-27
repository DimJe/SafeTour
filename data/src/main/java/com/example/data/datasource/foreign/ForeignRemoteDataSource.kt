package com.example.data.datasource.foreign

import com.example.data.BuildConfig
import com.example.data.api.foreign.ForeignApi
import com.example.data.api.foreign.ForeignXmlApi
import com.example.data.model.foreign.BasicData
import com.example.data.model.foreign.ContactData
import com.example.data.model.foreign.EntryData
import com.example.data.model.foreign.PoliceData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ForeignRemoteDataSource @Inject constructor(private val foreignApi: ForeignApi, private val foreignXmlApi: ForeignXmlApi) {

    private val serviceKey = BuildConfig.DATA_POTAL_API_KEY

    fun getContact(countryName: String): Flow<ContactData> = flow{
        val response = foreignApi.getContact(serviceKey, countryName = countryName)

        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getPolice(countryName: String): Flow<PoliceData> = flow{
        val response = foreignApi.getPolice(serviceKey, countryName = countryName)
        emit(response)

    }.flowOn(Dispatchers.IO)

    fun getBasic(countryName: String): Flow<Response<BasicData>> = flow{
        val response = foreignXmlApi.getBasic(serviceKey, countryName = countryName)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getEntryCondition(countryName: String): Flow<EntryData> = flow{
        val response = foreignApi.getEntryCondition(serviceKey, countryName = countryName)
        emit(response)
    }.flowOn(Dispatchers.IO)
}