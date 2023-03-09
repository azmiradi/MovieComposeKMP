package com.azmiradi.moviecomposekmp.data.network

import com.azmiradi.moviecomposekmp.data.model.MovieResponse
import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.model.MoviesResponse
import com.azmiradi.moviecomposekmp.data.utils.BASE_URL
import com.azmiradi.moviecomposekmp.data.utils.MOVIE_API_KEY
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class MovieApiImpl : MovieApi {
    private val client = HttpClient{
        expectSuccess = true
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }

        install(ContentNegotiation) {
            json()
        }

        install(Logging) {
            logger = object: Logger {
                override fun log(message: String) {
                    println("HTTP Client: $message")
                }
            }
            level = LogLevel.HEADERS
        }
    }

    private fun HttpRequestBuilder.movies(path: String) {
        url {
            takeFrom(BASE_URL)
            encodedPath = path
        }
    }

    override suspend fun getMovie(): MoviesResponse {
        return client.get {
            movies("3/movie/top_rated?api_key=${MOVIE_API_KEY}")
        }.body()
    }

    override suspend fun getMovieDetails(movieId:String): MovieResponse {
        return client.get {
            movies("3/movie/${movieId}?api_key=${MOVIE_API_KEY}")
        }.body()
    }
}