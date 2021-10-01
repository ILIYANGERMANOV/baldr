package ui.home

import androidx.compose.runtime.Composable
import com.benasher44.uuid.uuid4
import data.Media
import data.MediaType
import data.Product
import kotlinx.browser.window
import navigation.Route
import navigation.Routing
import org.jetbrains.compose.web.dom.Text

@Composable
fun Home() {
    Header()

    Text("URL: ${window.location.pathname}")

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

                id = uuid4(),
                orderId = 0.0
            )
        ) {
            Routing.navigate(Route.ProductDetails(productId = it.id))
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

                id = uuid4(),
                orderId = 0.1
            )
        ) {
            Routing.navigate(Route.ProductDetails(productId = it.id))
        }
    }
}