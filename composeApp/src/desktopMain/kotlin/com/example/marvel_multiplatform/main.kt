package com.example.marvel_multiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Marvel_Multiplatform",
    ) {
        App()
    }
}