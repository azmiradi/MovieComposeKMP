package com.azmiradi.moviecomposekmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return Application("MovieApp-KMP") {
        Column {
            Box(
                modifier = Modifier
                    .height(40.dp)
            )
            AppViewiOS()
        }
    }
}
