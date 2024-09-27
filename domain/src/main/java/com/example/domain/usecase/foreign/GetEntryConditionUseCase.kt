package com.example.domain.usecase.foreign

import com.example.domain.entity.foreign.EntryConditionEntity
import com.example.domain.repository.foreign.ForeignInfoRepository
import kotlinx.coroutines.flow.Flow

class GetEntryConditionUseCase(
    private val foreignInfoRepository: ForeignInfoRepository
) {
    operator fun invoke(name: String): Flow<Result<EntryConditionEntity>> = foreignInfoRepository.getEntryCondition(name)
}