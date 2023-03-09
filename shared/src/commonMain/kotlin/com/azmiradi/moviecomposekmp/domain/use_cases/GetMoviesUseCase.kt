package com.azmiradi.moviecomposekmp.domain.use_cases

import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.repository.HomeRepository
import com.azmiradi.moviecomposekmp.data.utils.Resource
import com.azmiradi.moviecomposekmp.data.utils.ioDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMoviesUseCase constructor(private val repository: HomeRepository) {
    operator fun invoke()
            : Flow<Resource<List<ResultItem?>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getMovie()

            if (response.results != null) {
                emit(Resource.Success(response.results))
            } else {
                emit(Resource.Error("not Found any Movies"))
            }
        } catch (io: Throwable) {
            emit(Resource.Error(io.message ?: "Error occur"))
        }
    }.flowOn(ioDispatcher)


}