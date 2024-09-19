package com.example.data.api.blog

import com.example.data.model.blog.BlogData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BlogApi {

    @GET("blog.json")
    suspend fun getBlog(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int = 20,
        @Query("start") start: Int = 1,
        @Query("sort") sort: String = "sim"
    ): BlogData
}