package ui.home

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img

@Composable
fun Header() {
    Div {
        Img(
            src = "assets/baldr.png",
            alt = "baldr",
            attrs = {
                style {
                    paddingLeft(16.px)
                    width(200.px)
                }
            }
        )
    }
}