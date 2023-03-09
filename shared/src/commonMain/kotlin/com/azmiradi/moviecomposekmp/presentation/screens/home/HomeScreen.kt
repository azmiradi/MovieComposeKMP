package com.azmiradi.moviecomposekmp.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.azmiradi.moviecomposekmp.Database
import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.utils.DataState
import com.azmiradi.moviecomposekmp.presentation.CustomColumn
import com.azmiradi.moviecomposekmp.presentation.ToastState
import com.azmiradi.moviecomposekmp.presentation.koin
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koin.get(),
    onClickMovie: (Int) -> Unit,
) {
    val state = remember {
        mutableStateOf<DataState<List<ResultItem>>>(DataState())
    }

    LaunchedEffect(Unit) {
        viewModel.movieState.onEach {
            state.value = it
        }.launchIn(this)
    }

    val toastState = remember {
        mutableStateOf<ToastState>(ToastState.Hidden)
    }
    if (state.value.error.isNotEmpty())
        toastState.value = ToastState.Shown(state.value.error)


    CustomColumn(
        toastState = toastState,
        isLoading = state.value.isLoading,
        baseViewModel = viewModel,
        modifier = Modifier.fillMaxSize(),
        requestData = {
            viewModel.getMovie()
        }, content = {
            Spacer(Modifier.height(14.dp))
            state.value.data?.let {
                LazyVerticalGrid(modifier = Modifier.padding(end = 16.dp, start = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(it) {
                            MovieItem(it) {
                                onClickMovie(it.id ?: 0)
                            }
                        }
                    }
                )
            }
            Spacer(Modifier.height(30.dp))
        })
}

