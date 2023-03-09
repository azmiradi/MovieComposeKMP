package com.azmiradi.moviecomposekmp.presentation.screens.details_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azmiradi.moviecomposekmp.Database
import com.azmiradi.moviecomposekmp.MovieQueries
import com.azmiradi.moviecomposekmp.data.model.MovieResponse
import com.azmiradi.moviecomposekmp.data.utils.DataState
import com.azmiradi.moviecomposekmp.data.utils.IMAGE_BASE_URL
import com.azmiradi.moviecomposekmp.presentation.CustomColumn
import com.azmiradi.moviecomposekmp.presentation.ToastState
import com.azmiradi.moviecomposekmp.presentation.koin
import com.azmiradi.moviecomposekmp.presentation.ui.Colors.YellowDark
import com.seiko.imageloader.rememberAsyncImagePainter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
internal fun MovieDetailsScreen(
    movieId: Int,
    viewModel: MovieDetailsViewModel = koin.get()
) {

    val isSaved = remember {
        mutableStateOf(false)
    }

    val state = remember {
        mutableStateOf<DataState<MovieResponse>>(DataState())
    }
    val toastState = remember {
        mutableStateOf<ToastState>(ToastState.Hidden)
    }
    if (state.value.error.isNotEmpty())
        toastState.value = ToastState.Shown(state.value.error)

    LaunchedEffect(Unit) {
        viewModel.movieState.onEach {
            state.value = it
        }.launchIn(this)

        viewModel.savedState.onEach {
            isSaved.value = it
        }.launchIn(this)
    }

    CustomColumn(
        toastState = toastState,
        isLoading = state.value.isLoading,
        horizontalAlignment = CenterHorizontally,
        baseViewModel = viewModel,
        modifier = Modifier.fillMaxSize(),
        requestData = {
            viewModel.getMovies(movieId.toString())
        },
        content = {
            state.value.data?.let {
                val painter =
                    rememberAsyncImagePainter(IMAGE_BASE_URL + it.posterPath)
                Box(Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painter,
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds
                    )
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                            .clip(
                                RoundedCornerShape(9.dp)
                            )
                            .background(Color.Gray.copy(0.5f)).padding(5.dp)
                    ) {
                        Text(
                            text = it.title ?: "-----",
                            maxLines = 3,
                            lineHeight = 20.sp,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = it.overview ?: "-----",
                            lineHeight = 20.sp,
                            color = Color.White,
                            modifier = Modifier.clip(
                                RoundedCornerShape(9.dp)
                            ).background(Color.Gray.copy(0.5f)).padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = (it.voteAverage ?: "-----").toString() + " Vote",
                            maxLines = 3,
                            lineHeight = 20.sp,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(modifier = Modifier.align(CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(if (isSaved.value) Color.Red else YellowDark),
                            shape = RoundedCornerShape(14.dp),
                            onClick = {
                                if (isSaved.value) {
                                    viewModel.deleteMove(it.id ?: 0)
                                    toastState.value =
                                        ToastState.Shown("Movie Deleted from Favorites")
                                } else {
                                    viewModel.addFavoriteMovie(it)
                                    toastState.value = ToastState.Shown("Movie Added To Favorite")
                                }
                                isSaved.value = !isSaved.value
                            }) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "",
                                tint = if (isSaved.value) Color.White else Color.Black
                            )
                            Text(
                                text = if (isSaved.value) "Delete From Favorite" else "Add To Favorite",
                                color = if (isSaved.value) Color.White else Color.Black
                            )
                        }
                    }

                }
            }
        })
}