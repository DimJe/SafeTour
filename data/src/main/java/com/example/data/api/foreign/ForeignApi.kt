package com.example.data.api.foreign

import com.example.data.model.foreign.ContactData
import com.example.data.model.foreign.EntryData
import com.example.data.model.foreign.PoliceData
import retrofit2.http.GET
import retrofit2.http.Query

interface ForeignApi {

    @GET("1262000/LocalContactService2/getLocalContactList2")
    suspend fun getContact(
        @Query("ServiceKey") serviceKey: String,
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("pageNo") pageNo: Int = 1,
        @Query("cond[country_nm::EQ]") countryName: String
    ): ContactData

    @GET("1262000/SecurityEnvironmentService/getSecurityEnvironmentList")
    suspend fun getPolice(
        @Query("serviceKey") serviceKey: String,
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("pageNo") pageNo: Int = 1,
        @Query("cond[country_nm::EQ]") countryName: String
    ): PoliceData

    @GET("1262000/EntranceVisaService2/getEntranceVisaList2")
    suspend fun getEntryCondition(
        @Query("serviceKey") serviceKey: String,
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("pageNo") pageNo: Int = 1,
        @Query("cond[country_nm::EQ]") countryName: String
    ): EntryData
}