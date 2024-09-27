package com.example.domain.repository.foreign

import com.example.domain.entity.foreign.BasicEntity
import com.example.domain.entity.foreign.ContactEntity
import com.example.domain.entity.foreign.EntryConditionEntity
import com.example.domain.entity.foreign.PoliceEntity
import kotlinx.coroutines.flow.Flow


interface ForeignInfoRepository {

    fun getEntryCondition(name: String): Flow<Result<EntryConditionEntity>>
    fun getBasicInfo(name: String): Flow<Result<BasicEntity>>
    fun getContact(name: String): Flow<Result<ContactEntity>>
    fun getPolice(name: String): Flow<Result<PoliceEntity>>
}