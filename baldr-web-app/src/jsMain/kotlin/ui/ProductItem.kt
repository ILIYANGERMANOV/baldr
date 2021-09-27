package ui

import androidx.compose.runtime.Composable
import components.MediaView
import data.Product
import org.jetbrains.compose.web.dom.Div

@Composable
fun ProductItem(product: Product) {
    Div {
        MediaView(product.media.first())
    }
}