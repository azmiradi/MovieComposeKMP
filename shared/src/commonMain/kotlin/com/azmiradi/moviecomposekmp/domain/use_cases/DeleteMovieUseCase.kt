package com.azmiradi.moviecomposekmp.domain.use_cases

import com.azmiradi.moviecomposekmp.data.repository.HomeRepository

class DeleteMovieUseCase constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(id: Int) = repository.deleteMovie(id)
}