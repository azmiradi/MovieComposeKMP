package com.azmiradi.moviecomposekmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.azmiradi.moviecomposekmp.presentation.CommonView


@Composable
fun AppViewAndroid() {
    val size = remember { mutableStateOf(IntSize.Zero) }

    Box(Modifier.fillMaxSize().onGloballyPositioned { coordinates ->
        size.value = coordinates.size
    }) {
        CommonView()
    }
}
