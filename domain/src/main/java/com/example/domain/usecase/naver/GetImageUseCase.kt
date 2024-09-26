package com.example.domain.usecase.naver

import com.example.domain.repository.naver.NaverRepository

class GetImageUseCase(
    private val naverRepository: NaverRepository
) {
    operator fun invoke(query: String) = naverRepository.getImage(query)
}