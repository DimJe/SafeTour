package com.example.data.repository.foreign

import com.example.data.BuildConfig
import com.example.data.datasource.foreign.ForeignRemoteDataSource
import com.example.data.model.foreign.toDomain
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
import javax.inject.Inject

class ForeignInfoRepositoryImpl @Inject constructor(private val foreignRemoteDataSource: ForeignRemoteDataSource): ForeignInfoRepository {


    override fun getEntryCondition(name: String): Flow<Result<EntryConditionEntity>> =  flow {
        try {
            val response = foreignRemoteDataSource.getEntryCondition(name).first()
            val entry = response.toDomain()
            emit(Result.success(entry))
        }catch (e: Exception){
            emit(Result.failure(e))
        }

    }.flowOn(Dispatchers.IO)

    override fun getBasicInfo(name: String): Flow<Result<BasicEntity>> = flow {
        try {
            val response = foreignRemoteDataSource.getBasic(name).first()
            val basic = response.body()?.body?.items?.item.orEmpty().map { it.toDomain() }
            emit(Result.success(basic.first()))
        }catch (e: Exception){
            emit(Result.failure(e))

        }
    }.flowOn(Dispatchers.IO)

    override fun getContact(name: String): Flow<Result<ContactEntity>> = flow {
        try {
            val response = foreignRemoteDataSource.getContact(name).first()
            val contact = response.toDomain()
            emit(Result.success(contact))
        }catch (e: Exception){
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getPolice(name: String): Flow<Result<PoliceEntity>> = flow {
        try {
            val response = foreignRemoteDataSource.getPolice(name).first()
            val police = response.toDomain()
            emit(Result.success(police))
        }catch (e: Exception){
            emit(Result.failure(e))
        }

    }.flowOn(Dispatchers.IO)

}