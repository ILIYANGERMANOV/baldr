package ui

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        Content()
    }
}

@Composable
fun Content() {
    Header()

    Products()
}



