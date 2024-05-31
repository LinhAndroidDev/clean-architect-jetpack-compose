package com.example.basiccodelab.features.domain.mapper

import com.example.basiccodelab.common.base.Mapper
import com.example.basiccodelab.data.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<Movie?, List<Movie.Result>?>{
    override fun fromMap(from: Movie?): List<Movie.Result>? {
        return from?.results?.map {
            Movie.Result(
                id = it.id,
                original_title = it.original_title,
                overview = it.overview,
                poster_path = it.poster_path,
                vote_average = it.vote_average
            )
        }
    }

}