import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
//    renderComposable(rootElementId = "root") {
//        Text("ccc")
//
//    }
    onWasmReady {
        Window {
            MainViewWeb()
        }
    }
}