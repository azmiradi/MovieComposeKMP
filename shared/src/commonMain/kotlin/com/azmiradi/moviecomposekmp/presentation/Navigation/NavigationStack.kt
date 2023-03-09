package com.azmiradi.moviecomposekmp.presentation.Navigation

import androidx.compose.runtime.mutableStateListOf

class NavigationStack<T>(initial: T) {
    private val stack = mutableStateListOf(initial)
    fun push(t: T) {
        stack.add(t)
    }

    fun back() {
        if(stack.size > 1) {
            // Always keep one element on the view stack
            stack.removeLast()
        }
    }

    fun lastWithIndex() = stack.withIndex().last()
}

sealed class Page

object HomePage : Page()
object FavoritePage : Page()


class DetailsPage(val movieID:Int): Page()