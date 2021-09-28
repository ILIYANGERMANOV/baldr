package ui

import androidx.compose.runtime.*
import css.Red
import data.Media
import data.MediaType
import data.Product
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        Content()
    }
}

@Composable
fun Content() {
    Header()

    var selectedProduct: Product? by remember {
        mutableStateOf(null)
    }

    selectedProduct?.let {
        H1({
            style {
                color(Red)
            }
        }) {
            Text(selectedProduct.toString())
        }
    }

    Products {
        ProductItem(
            Product(
                name = "Fidget Spinner",
                tagline = "stress reliever",
                price = 24.99,
                currency = "BGN",
                media = listOf(
                    Media(
                        url = "https://www.powerplanetonline.com/cdnassets/gyro_fidget_spinner_negro_ad_l.jpg",
                        alt = "fidget-spinner",
                        type = MediaType.IMAGE
                    )
                ),

                id = "p1",
                orderId = 0.0
            )
        ) {
            selectedProduct = it
        }

        ProductItem(
            Product(
                name = "Feel Flux",
                tagline = "kinetic toy",
                price = 64.99,
                currency = "BGN",
                media = listOf(
                    Media(
                        url = "https://www.youtube.com/embed/eq9bpu3zArI",
                        alt = "feel-flux",
                        type = MediaType.YOUTUBE_VIDEO
                    )
                ),

                id = "p2",
                orderId = 0.1
            )
        ) {
            selectedProduct = it
        }
    }
}



