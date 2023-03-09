package com.azmiradi.moviecomposekmp.domain.use_cases

import com.azmiradi.moviecomposekmp.Movie
import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.repository.HomeRepository
import com.azmiradi.moviecomposekmp.data.utils.Resource
import com.azmiradi.moviecomposekmp.data.utils.ioDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AddFavoriteMovieUseCase constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(movie: Movie) {
        repository.addMovieToFavorites(movie)
    }
}
