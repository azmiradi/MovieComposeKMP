package com.azmiradi.moviecomposekmp.presentation.screens.details_screen

import com.azmiradi.moviecomposekmp.data.model.MovieResponse
import com.azmiradi.moviecomposekmp.data.model.toMovie
import com.azmiradi.moviecomposekmp.data.utils.DataState
import com.azmiradi.moviecomposekmp.data.utils.Resource
import com.azmiradi.moviecomposekmp.domain.use_cases.AddFavoriteMovieUseCase
import com.azmiradi.moviecomposekmp.domain.use_cases.CheckIsSavedUseCase
import com.azmiradi.moviecomposekmp.domain.use_cases.DeleteMovieUseCase
import com.azmiradi.moviecomposekmp.domain.use_cases.GetMovieDetailsUseCase
import com.azmiradi.moviecomposekmp.presentation.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MovieDetailsViewModel constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val checkIsSavedUseCase: CheckIsSavedUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : BaseViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private var job: Job? = null
    private val _movieState = MutableStateFlow(DataState<MovieResponse>())
    val movieState: StateFlow<DataState<MovieResponse>> = _movieState


    private val _savedState = MutableStateFlow(false)
    val savedState: StateFlow<Boolean> = _savedState


    fun getMovies(movieID: String) {
        job?.cancel()
        job = movieDetailsUseCase(movieID).onEach {
            when (it) {
                is Resource.Loading -> {
                    _movieState.value = DataState(isLoading = true)
                }

                is Resource.Error -> {
                    _movieState.value = DataState(error = it.message ?: "Error Occur!")
                }

                is Resource.Success -> {
                    checkIsSaved(it.data?.id ?: 0)
                    _movieState.value = DataState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun checkIsSaved(id: Int) {
        viewModelScope.launch {
            _savedState.value = checkIsSavedUseCase(id)
        }
    }

    fun addFavoriteMovie(movieResponse: MovieResponse) {
        viewModelScope.launch {
            try {
                addFavoriteMovieUseCase(movieResponse.toMovie())
            } catch (e: Exception) {
                println("Exception:: " + e.message)
            }
        }
    }

    fun deleteMove(id: Int) {
        viewModelScope.launch {
            deleteMovieUseCase(id)
        }
    }

    override fun resetState() {
        viewModelScope.cancel()
        _movieState.value = DataState()
    }

}

