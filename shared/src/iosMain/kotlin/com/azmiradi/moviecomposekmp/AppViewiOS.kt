package com.azmiradi.moviecomposekmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.azmiradi.moviecomposekmp.presentation.CommonView
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.util.DebugLogger
import com.seiko.imageloader.util.LogPriority

@Composable
internal fun AppViewiOS() {
    val size = remember { mutableStateOf(IntSize.Zero) }
    Box(Modifier.fillMaxSize().onGloballyPositioned { coordinates ->
        size.value = coordinates.size
    }) {
        CompositionLocalProvider(
            LocalImageLoader provides ImageLoader {
                logger = DebugLogger(LogPriority.VERBOSE)
                components {
                    setupDefaultComponents(imageScope)
                }
                interceptor {
                    memoryCacheConfig {
                        maxSizePercent(0.25)
                    }
                }
            },
        ) {
            CommonView()
         }
    }
}