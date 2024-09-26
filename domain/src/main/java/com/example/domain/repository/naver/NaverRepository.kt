package com.example.domain.repository.naver

import com.example.domain.entity.naver.BlogEntity
import com.example.domain.entity.naver.ImageEntity
import kotlinx.coroutines.flow.Flow

interface NaverRepository {

    fun getBlog(query: String): Flow<Result<BlogEntity>>
    fun getImage(query: String): Flow<Result<ImageEntity>>

}