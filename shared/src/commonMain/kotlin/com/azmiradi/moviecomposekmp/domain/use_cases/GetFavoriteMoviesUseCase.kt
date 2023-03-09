package com.azmiradi.moviecomposekmp.domain.use_cases

import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.repository.HomeRepository
import com.azmiradi.moviecomposekmp.data.utils.Resource
import com.azmiradi.moviecomposekmp.data.utils.ioDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetFavoriteMoviesUseCase constructor(private val repository: HomeRepository) {
    operator fun invoke()
            : Flow<Resource<List<ResultItem>?>> = flow {
        try {
            val response = repository.getFavoritesMovies()
            val moviesList =
                response?.map {
                    ResultItem(
                        id = it.id.toInt(), title = it.title, overview = it.overview,
                        posterPath = it.posterPath,
                        voteAverage = it.voteAverage
                    )
                }
            emit(Resource.Success(moviesList))
        } catch (ex: Throwable) {
            emit(Resource.Error(ex.message ?: ""))
        }
    }.flowOn(ioDispatcher)
}
