package com.example.basiccodelab.common.base

import com.example.basiccodelab.common.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Flow<ApiState<T>> = flow {
        emit(ApiState.Loading)
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                emit(ApiState.Success(response.body()!!))
            } else {
                emit(ApiState.Failure(Throwable(response.message())))
            }
        } catch (e: Exception) {
            emit(ApiState.Failure(Exception(e)))
        }
    }.catch { e ->
        emit(ApiState.Failure(Exception(e)))
    }.flowOn(Dispatchers.IO)
}