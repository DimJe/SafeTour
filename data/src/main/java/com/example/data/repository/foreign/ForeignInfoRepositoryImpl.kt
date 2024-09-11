package com.example.data.repository.foreign

import com.example.data.datasource.ForeignRemoteDataSource
import com.example.data.model.toDomain
import com.example.domain.entity.foreign.BasicEntity
import com.example.domain.entity.foreign.ContactEntity
import com.example.domain.entity.foreign.EntryConditionEntity
import com.example.domain.entity.foreign.PoliceEntity
import com.example.domain.repository.foreign.ForeignInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ForeignInfoRepositoryImpl(private val foreignRemoteDataSource: ForeignRemoteDataSource): ForeignInfoRepository {

    override fun getEntryCondition(serviceKey: String, name: String): Flow<Result<EntryConditionEntity>> =  flow {
        try {
            val response = foreignRemoteDataSource.getEntryCondition(serviceKey, name).first()
            val entry = response.toDomain()
            emit(Result.success(entry))
        }catch (e: Exception){
            emit(Result.failure(e))
        }

    }.flowOn(Dispatchers.IO)

    override fun getBasicInfo(serviceKey: String, name: String): Flow<Result<BasicEntity>> = flow {
        try {
            val response = foreignRemoteDataSource.getBasic(serviceKey, name).first()
            val basic = response.body()?.body?.items?.item.orEmpty().map { it.toDomain() }
            emit(Result.success(basic.first()))
        }catch (e: Exception){
            emit(Result.failure(e))

        }
    }.flowOn(Dispatchers.IO)

    override fun getContact(serviceKey: String, name: String): Flow<Result<ContactEntity>> = flow {
        try {
            val response = foreignRemoteDataSource.getContact(serviceKey, name).first()
            val contact = response.toDomain()
            emit(Result.success(contact))
        }catch (e: Exception){
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getPolice(serviceKey: String, name: String): Flow<Result<PoliceEntity>> = flow {
        try {
            val response = foreignRemoteDataSource.getPolice(serviceKey, name).first()
            val police = response.toDomain()
            emit(Result.success(police))
        }catch (e: Exception){
            emit(Result.failure(e))
        }

    }.flowOn(Dispatchers.IO)

}