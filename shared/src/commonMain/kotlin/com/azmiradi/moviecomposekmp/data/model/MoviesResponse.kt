package com.azmiradi.moviecomposekmp.data.model

import com.azmiradi.moviecomposekmp.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName("page")
    val page: Int? = null,

    @SerialName("total_pages")
    val totalPages: Int? = null,

    @SerialName("results")
    val results: List<ResultItem?>? = null,

    @SerialName("total_results")
    val totalResults: Int? = null
)

@Serializable
data class ResultItem(
    @SerialName("overview")
    val overview: String? = null,

    @SerialName("original_language")
    val originalLanguage: String? = null,

    @SerialName("original_title")
    val originalTitle: String? = null,

    @SerialName("video")
    val video: Boolean? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("genre_ids")
    val genreIds: List<Int?>? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    @SerialName("popularity")
    val popularity: String? = null,

    @SerialName("vote_average")
    val voteAverage: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("adult")
    val adult: Boolean? = null,

    @SerialName("vote_count")
    val voteCount: Int? = null
)

fun MovieResponse.toMovie() =
    Movie(
        (id ?: 0).toLong(),
        overview ?: "",
        title ?: "",
        posterPath ?: "",
        voteAverage ?: ""
    )



