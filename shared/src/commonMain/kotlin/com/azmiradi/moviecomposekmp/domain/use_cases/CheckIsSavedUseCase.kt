package com.azmiradi.moviecomposekmp.domain.use_cases

import com.azmiradi.moviecomposekmp.data.repository.HomeRepository

class CheckIsSavedUseCase constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(id: Int)
            : Boolean = repository.movieIsSaved(id)
}