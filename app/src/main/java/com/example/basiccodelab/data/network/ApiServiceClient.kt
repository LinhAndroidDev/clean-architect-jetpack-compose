package com.example.basiccodelab.data.network

import com.example.basiccodelab.data.model.Movie
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceClient {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    @GET("discover/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    suspend fun getMovies(): Response<Movie>
}