package com.example.basiccodelab.data.model

data class Movie(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) {
    data class Result(
        val id: Long?,
        val original_title: String?,
        val overview: String?,
        val poster_path: String?,
        val vote_average: Float?
    )
}