package com.example.domain.usecase.foreign

import com.example.domain.repository.foreign.ForeignInfoRepository

class GetContactUseCase(
    private val foreignInfoRepository: ForeignInfoRepository
) {
    operator fun invoke(serviceKey:String, name: String) = foreignInfoRepository.getContact(serviceKey, name)

}