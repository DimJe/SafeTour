package com.example.data.datasource.blog

import com.example.data.api.blog.BlogApi
import com.example.data.model.blog.BlogData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BlogRemoteDataSource(
    private val blogApi: BlogApi
) {
    fun getBlog(clientId: String, clientSecret: String, query: String) : Flow<BlogData> = flow {
        val response = blogApi.getBlog(clientId, clientSecret, query)
        emit(response)
    }.flowOn(Dispatchers.IO)
}