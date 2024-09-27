package com.example.domain.usecase.foreign

import com.example.domain.repository.foreign.ForeignInfoRepository

class GetContactUseCase(
    private val foreignInfoRepository: ForeignInfoRepository
) {
    operator fun invoke(name: String) = foreignInfoRepository.getContact(name)

}