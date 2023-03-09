package com.azmiradi.moviecomposekmp.domain.use_cases

import com.azmiradi.moviecomposekmp.data.model.MovieResponse
import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.repository.HomeRepository
import com.azmiradi.moviecomposekmp.data.utils.Resource
import com.azmiradi.moviecomposekmp.data.utils.ioDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieDetailsUseCase constructor(private val repository: HomeRepository) {
    operator fun invoke(movieId: String)
            : Flow<Resource<MovieResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getMovieDetails(movieId)

            emit(Resource.Success(response))
        } catch (io: Throwable) {
            emit(Resource.Error(io.message ?: "Error occur"))
        }
    }.flowOn(ioDispatcher)
}