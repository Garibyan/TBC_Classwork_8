package com.garibyan.armen.tbc_task_8.common.base

import com.garibyan.armen.tbc_task_8.common.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T> saveApiCall(apiCall: suspend () -> Response<T>) = flow {
        emit(Resource.Loading)
        try {
            if (apiCall.invoke().isSuccessful && apiCall.invoke().body() != null) {
                emit(Resource.Success(apiCall.invoke().body()!!))
            } else {
                emit(Resource.Error(apiCall.invoke().message()))
            }
        } catch (e: Throwable) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}