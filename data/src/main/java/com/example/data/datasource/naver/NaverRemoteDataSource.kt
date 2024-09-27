package com.example.data.datasource.naver

import com.example.data.BuildConfig
import com.example.data.api.naver.NaverApi
import com.example.data.model.naver.BlogData
import com.example.data.model.naver.ImageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NaverRemoteDataSource @Inject constructor(
    private val naverApi: NaverApi
) {
    private val clientId = BuildConfig.NAVER_CLIENT_ID
    private val clientSecret = BuildConfig.NAVER_CLIENT_SECRET

    fun getBlog(query: String) : Flow<BlogData> = flow {
        val response = naverApi.getBlog(clientId, clientSecret, "$query 여행지 추천")
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getImage(query: String): Flow<ImageData> = flow {
        val response = naverApi.getImage(clientId, clientSecret, "$query 관광지 사진")
        emit(response)
    }.flowOn(Dispatchers.IO)
}