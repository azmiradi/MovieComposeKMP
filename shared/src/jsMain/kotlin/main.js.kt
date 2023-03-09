import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.azmiradi.moviecomposekmp.presentation.CommonView
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.jetbrains.compose.web.dom.Text


@Composable
fun MainViewWeb() {
    CompositionLocalProvider(
        LocalImageLoader provides ImageLoader {
            components {
                setupDefaultComponents(CoroutineScope(SupervisorJob() + Dispatchers.Default))
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

