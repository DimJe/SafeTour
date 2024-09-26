package com.example.data.repository.naver

import com.example.data.BuildConfig
import com.example.data.datasource.naver.NaverRemoteDataSource
import com.example.data.model.naver.toDomain
import com.example.domain.entity.naver.BlogEntity
import com.example.domain.entity.naver.ImageEntity
import com.example.domain.repository.naver.NaverRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NaverRepositoryImpl(
    private val naverRemoteDataSource: NaverRemoteDataSource
): NaverRepository {
    override fun getBlog(query: String): Flow<Result<BlogEntity>> = flow{
        try {
            val response = naverRemoteDataSource.getBlog(BuildConfig.NAVER_CLIENT_ID, BuildConfig.NAVER_CLIENT_SECRET,query).first()
            val blog = response.toDomain()
            emit(Result.success(blog))
        }catch (e: Exception){
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getImage(query: String): Flow<Result<ImageEntity>> = flow{
        try {
            val response = naverRemoteDataSource.getImage(BuildConfig.NAVER_CLIENT_ID, BuildConfig.NAVER_CLIENT_SECRET,query).first()
            val image = response.toDomain()
            emit(Result.success(image))
        }catch (e: Exception){
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)
}