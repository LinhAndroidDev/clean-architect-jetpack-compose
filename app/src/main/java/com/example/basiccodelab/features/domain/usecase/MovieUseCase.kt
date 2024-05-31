package com.example.basiccodelab.features.domain.usecase

import com.example.basiccodelab.common.ApiState
import com.example.basiccodelab.common.map
import com.example.basiccodelab.data.model.Movie
import com.example.basiccodelab.data.repository.MovieRepository
import com.example.basiccodelab.features.domain.mapper.MovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) {
    suspend fun getMovies() : Flow<ApiState<List<Movie.Result>?>> {
        return movieRepository.getMovies().map { results ->
            results.map {
                movieMapper.fromMap(it)
            }
        }
    }
}