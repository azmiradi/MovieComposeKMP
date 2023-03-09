package com.azmiradi.moviecomposekmp.domain.repository

import com.azmiradi.moviecomposekmp.Movie
import com.azmiradi.moviecomposekmp.data.local.SharedDatabase
import com.azmiradi.moviecomposekmp.data.network.MovieApi
import com.azmiradi.moviecomposekmp.data.repository.HomeRepository

internal class HomeRepositoryImpl
constructor(
    private val movieApi: MovieApi,
    private val db: SharedDatabase
) : HomeRepository {
    override suspend fun getMovie() = movieApi.getMovie()

    override suspend fun getMovieDetails(movieId: String) = movieApi.getMovieDetails(movieId)

    override suspend fun getFavoritesMovies() =
        db.invoke()?.movieQueries?.selectAll()?.executeAsList()

    override suspend fun addMovieToFavorites(movie: Movie) {
        db.invoke()?.movieQueries?.insert(
            movie.id,
            movie.overview,
            movie.title,
            movie.posterPath,
            movie.voteAverage
        )
    }

    override suspend fun movieIsSaved(id: Int) =
        db.invoke()?.movieQueries?.selectByID(id.toLong())?.executeAsOneOrNull() != null

    override suspend fun deleteMovie(id: Int) {
        db.invoke()?.movieQueries?.deleteByID(id.toLong())
    }

}