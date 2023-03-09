package com.azmiradi.moviecomposekmp.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.utils.DataState
import com.azmiradi.moviecomposekmp.data.utils.Resource
import com.azmiradi.moviecomposekmp.domain.use_cases.GetMoviesUseCase
import com.azmiradi.moviecomposekmp.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*

class HomeViewModel constructor(private val getMoviesUseCase: GetMoviesUseCase) : BaseViewModel() {
    private var job: Job? = null
    private val _movieState = MutableStateFlow(DataState<List<ResultItem>>())
    val movieState: StateFlow<DataState<List<ResultItem>>> = _movieState
    private val viewModelScope = CoroutineScope(Dispatchers.Main)


    fun getMovie() {
        job?.cancel()
        job = getMoviesUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    val movieList = it.data?.mapNotNull {
                        it
                    }

                    movieList?.let {
                        _movieState.value = DataState(data = it)
                        println("Data:: 1-> " + movieState.value.data?.size)
                    }
                    println("Data:: 2-> " + movieList?.size)

                    //_movieState.value = DataState(error = "Empty Movies")
                }
                is Resource.Error -> {
                    _movieState.value = DataState(error = it.message ?: "Error Occur!")

                }
                is Resource.Loading -> {
                    _movieState.value = DataState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun resetState() {
        viewModelScope.cancel()
        _movieState.value = DataState()
    }


}