package com.example.domain.repository.blog

import com.example.domain.entity.blog.BlogEntity
import kotlinx.coroutines.flow.Flow

interface BlogRepository {

    fun getBlog(query: String): Flow<Result<BlogEntity>>

}