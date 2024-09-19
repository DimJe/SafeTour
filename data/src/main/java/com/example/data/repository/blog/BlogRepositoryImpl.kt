package com.example.data.repository.blog

import com.example.data.BuildConfig
import com.example.data.datasource.blog.BlogRemoteDataSource
import com.example.data.model.blog.toDomain
import com.example.domain.entity.blog.BlogEntity
import com.example.domain.repository.blog.BlogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BlogRepositoryImpl(
    private val blogRemoteDataSource: BlogRemoteDataSource
): BlogRepository {
    override fun getBlog(query: String): Flow<Result<BlogEntity>> = flow{
        try {
            val response = blogRemoteDataSource.getBlog(BuildConfig.NAVER_CLIENT_ID, BuildConfig.NAVER_CLIENT_SECRET,query).first()
            val blog = response.toDomain()
            emit(Result.success(blog))
        }catch (e: Exception){
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)
}