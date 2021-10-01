package ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import com.benasher44.uuid.uuid4
import data.Media
import data.MediaType
import data.Product
import io.ktor.client.engine.js.*
import kotlinx.coroutines.launch
import logic.restClient
import navigation.Route
import navigation.Routing
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text
import rest.RestClient
import rest.request.UploadProductRequest
import util.log

@Composable
fun Home() {
    Header()

    Products {
        var products: List<Product> by remember {
            mutableStateOf(emptyList())
        }

        LaunchedEffect(true) {
            products = restClient().getProducts().products
        }

        for (product in products) {
            ProductItem(product) {
                Routing.navigate(
                    Route.ProductDetails(
                        productId = product.id
                    )
                )
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()
    Button(
        attrs = {
            onClick {
                val productId = uuid4()
                val newProduct = Product(
                    id = productId,
                    name = "Fidget Spinner",
                    tagline = "stress releiver",
                    description = "A cool desktop toy that'll take your stress away.",
                    price = 24.99,
                    currency = "BGN",
                    media = listOf(
                        Media(
                            productId = productId,
                            url = "https://www.youtube.com/embed/e6UG4jrfGNM",
                            type = MediaType.YOUTUBE_VIDEO,
                            orderNum = 0.0
                        ),
                        Media(
                            productId = productId,
                            url = "https://media.karousell.com/media/photos/products/2020/6/13/gold_fidget_spinner_1592030226_d69c901e_progressive.jpg",
                            type = MediaType.IMAGE,
                            orderNum = 0.1
                        )
                    )
                )

                log("Add product.onClick {}")

                coroutineScope.launch {
                    log("Sending upload product request...")

                    restClient().uploadProduct(
                        UploadProductRequest(
                            product = newProduct,
                            password = RestClient.BALDR_PASSWORD
                        )
                    )
                }
            }
        }
    ) {
        Text("Add product")
    }
}