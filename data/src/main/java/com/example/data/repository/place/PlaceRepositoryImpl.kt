package com.example.data.repository.place

import com.example.data.datasource.place.PlaceRemoteDataSource
import com.example.data.model.place.toDomain
import com.example.domain.entity.place.PlaceEntity
import com.example.domain.repository.place.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PlaceRepositoryImpl(private val placeRemoteDataSource: PlaceRemoteDataSource) :
    PlaceRepository {
    override fun getPlace(name: String): Flow<Result<List<PlaceEntity>>> = flow{
        try {
            val response = placeRemoteDataSource.getPlace(name).first()
            val data = response.map { it.toDomain() }
            emit(Result.success(data))
        }catch (e: Exception){
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

}