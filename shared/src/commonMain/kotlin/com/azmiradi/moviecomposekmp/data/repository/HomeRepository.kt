package com.azmiradi.moviecomposekmp.data.repository

import com.azmiradi.moviecomposekmp.Movie
import com.azmiradi.moviecomposekmp.data.model.MovieResponse
import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.model.MoviesResponse

interface HomeRepository {
    suspend fun getMovie(): MoviesResponse
    suspend fun getMovieDetails(movieId: String): MovieResponse
    suspend fun getFavoritesMovies(): List<Movie>?
    suspend fun addMovieToFavorites(movie: Movie)
    suspend fun movieIsSaved(id:Int):Boolean
    suspend fun deleteMovie(id: Int)
}