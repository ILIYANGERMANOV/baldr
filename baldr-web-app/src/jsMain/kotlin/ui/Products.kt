package ui

import androidx.compose.runtime.Composable
import data.Media
import data.MediaType
import data.Product

@Composable
fun Products() {
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
}