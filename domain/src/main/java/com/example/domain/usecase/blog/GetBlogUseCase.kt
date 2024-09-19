package com.example.domain.usecase.blog

import com.example.domain.repository.blog.BlogRepository

class GetBlogUseCase(
    private val blogRepository: BlogRepository
) {
    operator fun invoke(query: String) = blogRepository.getBlog(query)

}