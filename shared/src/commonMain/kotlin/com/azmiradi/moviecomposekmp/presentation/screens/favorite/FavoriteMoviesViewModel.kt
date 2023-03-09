package com.azmiradi.moviecomposekmp.presentation.screens.favorite

import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.utils.DataState
import com.azmiradi.moviecomposekmp.data.utils.Resource
import com.azmiradi.moviecomposekmp.domain.use_cases.GetFavoriteMoviesUseCase
import com.azmiradi.moviecomposekmp.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class FavoriteMoviesViewModel constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
) : BaseViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private var job: Job? = null
    private val _movieState = MutableStateFlow(DataState<List<ResultItem>>())
    val movieState: StateFlow<DataState<List<ResultItem>>> = _movieState

    fun getMovies() {
        job?.cancel()
        job = getFavoriteMoviesUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _movieState.value = DataState(isLoading = true)
                }

                is Resource.Error -> {
                    _movieState.value = DataState(error = it.message ?: "Error Occur!")
                }

                is Resource.Success -> {
                    _movieState.value = DataState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun resetState() {
        viewModelScope.cancel()
        _movieState.value = DataState()
    }

}

