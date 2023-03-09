package com.azmiradi.moviecomposekmp.presentation.screens.home

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.azmiradi.moviecomposekmp.presentation.Navigation.FavoritePage
import com.azmiradi.moviecomposekmp.presentation.Navigation.HomePage
import com.azmiradi.moviecomposekmp.presentation.Navigation.NavigationStack
import com.azmiradi.moviecomposekmp.presentation.Navigation.Page
import com.azmiradi.moviecomposekmp.presentation.ui.Colors

@Composable
internal fun BottomNavigationBar(navigationStack: NavigationStack<Page>) {
    val items = listOf(
        Pair("Home", HomePage),
        Pair("Favorite", FavoritePage),
    )
    BottomNavigation(backgroundColor = Colors.YellowDark) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {

                },
                alwaysShowLabel = true,
                selected = false,
                label = {
                    Text(item.first, color = Color.Black)
                },
                onClick = {
                    if (item.second != navigationStack.lastWithIndex().value)
                        navigationStack.push(item.second)
                }
            )
        }
    }
}

