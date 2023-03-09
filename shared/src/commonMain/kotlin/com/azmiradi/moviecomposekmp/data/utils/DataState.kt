package com.azmiradi.moviecomposekmp.data.utils


data class DataState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = "",
 )
