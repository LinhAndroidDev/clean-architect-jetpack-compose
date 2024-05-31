package com.example.basiccodelab.data.network.repository

import com.example.basiccodelab.common.ApiState
import com.example.basiccodelab.common.base.BaseRepository
import com.example.basiccodelab.data.model.Movie
import com.example.basiccodelab.data.network.ApiServiceClient
import com.example.basiccodelab.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiServiceClient: ApiServiceClient
) : MovieRepository, BaseRepository() {
    override suspend fun getMovies(): Flow<ApiState<Movie>> = safeApiCall {
        apiServiceClient.getMovies()
    }
}