package tim.huang.ktor_cloudflare_worker

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ktor-cloudflare-worker",
    ) {
        App()
    }
}