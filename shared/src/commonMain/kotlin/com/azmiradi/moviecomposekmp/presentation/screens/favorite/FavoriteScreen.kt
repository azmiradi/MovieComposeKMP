package com.azmiradi.moviecomposekmp.presentation.screens.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.utils.DataState
import com.azmiradi.moviecomposekmp.presentation.koin
import com.azmiradi.moviecomposekmp.presentation.screens.home.MovieItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
internal fun FavoriteScreen(
    viewModel: FavoriteMoviesViewModel = koin.get(),
    onClickMovie: (Int) -> Unit
) {
    val state = remember {
        mutableStateOf<DataState<List<ResultItem>>>(DataState())
    }

    LaunchedEffect(Unit) {
        viewModel.getMovies()
        viewModel.movieState.onEach {
            state.value = it
        }.launchIn(this)
    }


    if (state.value.data.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                "Not have Favorites Movies",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        LazyVerticalGrid(modifier = Modifier.padding(
            end = 16.dp, start = 16.dp, top = 14.dp,
            bottom = 30.dp),

            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            columns = GridCells.Fixed(2),
            content = {
                items(state.value.data ?: emptyList()) {
                    MovieItem(it) {
                        onClickMovie(it.id ?: 0)
                    }
                }
            }
        )
    }

}