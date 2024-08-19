package io.keeppro.kcw

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.keeppro.kcw.sample.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ktor-cloudflare-worker",
    ) {
        App()
    }
}