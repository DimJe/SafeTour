package com.example.domain.usecase.foreign

import com.example.domain.repository.foreign.ForeignInfoRepository

class GetBasicInfoUseCase(
    private val foreignInfoRepository: ForeignInfoRepository
) {
    operator fun invoke(serviceKey:String, name: String) = foreignInfoRepository.getBasicInfo(serviceKey, name)

}