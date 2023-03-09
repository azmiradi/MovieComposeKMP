package com.azmiradi.moviecomposekmp.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.azmiradi.moviecomposekmp.data.di.Koin
import com.azmiradi.moviecomposekmp.presentation.Navigation.*
import com.azmiradi.moviecomposekmp.presentation.screens.details_screen.MovieDetailsScreen
import com.azmiradi.moviecomposekmp.presentation.screens.favorite.FavoriteScreen
import com.azmiradi.moviecomposekmp.presentation.screens.home.BottomNavigationBar
import com.azmiradi.moviecomposekmp.presentation.screens.home.HomeScreen
import com.azmiradi.moviecomposekmp.presentation.ui.Colors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

val koin = Koin.initialize().koin


@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun CommonView() {
    val rootPage = HomePage
    val navigationStack = remember { NavigationStack<Page>(rootPage) }

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Colors.YellowDark) {
            IconButton(onClick = {
                if (navigationStack.lastWithIndex().value != rootPage)
                    navigationStack.back()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        }
    }, modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navigationStack)
        }) {
        AnimatedContent(modifier = Modifier.padding(it),
            targetState = navigationStack.lastWithIndex(),
            transitionSpec = {
                val previousIdx = initialState.index
                val currentIdx = targetState.index
                val multiplier = if (previousIdx < currentIdx) 1 else -1
                slideInHorizontally { w -> multiplier * w } with
                        slideOutHorizontally { w -> multiplier * -1 * w }
            }) { (_, page) ->
            when (page) {
                is HomePage -> {
                    HomeScreen(onClickMovie = {
                        navigationStack.push(DetailsPage(it))
                    })
                }
                is DetailsPage -> {
                    MovieDetailsScreen(page.movieID)
                }
                is FavoritePage -> {
                    FavoriteScreen(onClickMovie = {
                        navigationStack.push(DetailsPage(it))
                    })
                }
            }
        }
    }
}

