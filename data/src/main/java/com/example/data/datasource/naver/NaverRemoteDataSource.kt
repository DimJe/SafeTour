package com.example.data.datasource.naver

import com.example.data.api.naver.NaverApi
import com.example.data.model.naver.BlogData
import com.example.data.model.naver.ImageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NaverRemoteDataSource(
    private val naverApi: NaverApi
) {
    fun getBlog(clientId: String, clientSecret: String, query: String) : Flow<BlogData> = flow {
        val response = naverApi.getBlog(clientId, clientSecret, "$query 여행지 추천")
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getImage(clientId: String, clientSecret: String, query: String): Flow<ImageData> = flow {
        val response = naverApi.getImage(clientId, clientSecret, "$query 관광지 사진")
        emit(response)
    }.flowOn(Dispatchers.IO)
}