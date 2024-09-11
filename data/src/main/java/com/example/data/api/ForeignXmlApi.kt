package com.example.data.api

import com.example.data.model.BasicData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForeignXmlApi {
    @GET("/1262000/CountryBasicService/getCountryBasicList")
    suspend fun getBasic(
        @Query("serviceKey") serviceKey: String,
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("pageNo") pageNo: Int = 1,
        @Query("countryName") countryName: String
    ): Response<BasicData>
}