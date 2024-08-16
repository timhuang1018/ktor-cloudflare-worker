package tim.huang.kcw

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ktor-cloudflare-worker",
    ) {
        tim.huang.kcw.sample.App()
    }
}