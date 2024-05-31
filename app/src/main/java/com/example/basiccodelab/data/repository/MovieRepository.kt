package com.example.basiccodelab.data.repository

import com.example.basiccodelab.common.ApiState
import com.example.basiccodelab.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<ApiState<Movie>>
}