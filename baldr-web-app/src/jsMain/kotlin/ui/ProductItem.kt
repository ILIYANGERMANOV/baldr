package ui

import androidx.compose.runtime.Composable
import components.MediaView
import css.Red
import data.Product
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text

@Composable
fun ProductItem(product: Product) {
    Div({
        style {
            backgroundColor(Color("#f4f4f4"))
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            alignContent(AlignContent.Center)
            justifyContent(JustifyContent.Center)
            marginLeft(32.px)
            marginRight(32.px)
        }
    }) {
        MediaView(
            media = product.media.first(),
            width = 420,
            height = 420
        )

        H1({
            style {
                backgroundColor(Red)
                textAlign("center")
            }
        }) {
            Text(product.name)
        }

        product.tagline?.let {
            H2 {
                Text(it)
            }
        }
    }
}