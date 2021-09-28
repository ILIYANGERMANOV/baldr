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
            width(600.px)
            backgroundColor(Color("#f4f4f4"))
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            alignContent(AlignContent.Center)
            justifyContent(JustifyContent.Center)
            attr("margin", "auto")
        }
    }) {
        MediaView(product.media.first())

        H1({
            style {
                backgroundColor(Red)
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