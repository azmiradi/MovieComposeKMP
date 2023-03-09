package com.azmiradi.moviecomposekmp.data.network

import com.azmiradi.moviecomposekmp.data.model.MovieResponse
import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.model.MoviesResponse


interface MovieApi {
    suspend fun getMovie(): MoviesResponse
    suspend fun getMovieDetails(movieId:String): MovieResponse
}