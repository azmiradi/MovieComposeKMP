package com.azmiradi.moviecomposekmp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun CustomColumn(
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    modifier: Modifier,
    isLoading: Boolean,
    toastState: MutableState<ToastState>,
    baseViewModel: BaseViewModel,
    content: @Composable ColumnScope.() -> Unit,
    requestData: () -> Unit
) {
    var snackbarVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Surface (Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            modifier = modifier,
            content = content
        )

        if (isLoading) {
           ProgressBar()
        }

        Toast(toastState)
    }
    DisposableEffect(Unit) {
        requestData()
        onDispose {
            baseViewModel.resetState()
        }
    }

}