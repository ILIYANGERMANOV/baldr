package ui

import androidx.compose.runtime.Composable
import data.Media
import data.MediaType
import data.Product
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        Content()
    }
}

@Composable
fun Content() {
    Header()

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
        )

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
        )
    }
}



