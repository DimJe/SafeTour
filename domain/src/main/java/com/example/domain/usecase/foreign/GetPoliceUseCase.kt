package com.example.domain.usecase.foreign

import com.example.domain.repository.foreign.ForeignInfoRepository

class GetPoliceUseCase(
    private val foreignInfoRepository: ForeignInfoRepository
) {
    operator fun invoke(serviceKey:String, name: String) = foreignInfoRepository.getPolice(serviceKey, name)
}