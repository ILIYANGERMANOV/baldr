package ui.home

import androidx.compose.runtime.*
import com.benasher44.uuid.uuid4
import data.Media
import data.MediaType
import data.Product
import kotlinx.coroutines.launch
import logic.restClient
import core.Route
import core.Routing
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text
import rest.RestClient
import rest.request.UploadProductRequest
import util.log

@Composable
fun HomeUi() {
    Header()

    var products: List<Product> by remember {
        mutableStateOf(emptyList())
    }

    LaunchedEffect(true) {
        products = restClient().getProducts().products
    }

    for (i in products.indices step 2) {
        ProductsRow {
            ProductItem(products[i]) {
                Routing.navigate(
                    Route.ProductDetails(
                        productId = it.id
                    )
                )
            }

            products.getOrNull(i + 1)?.let { secondProduct ->
                ProductItem(secondProduct) {
                    Routing.navigate(
                        Route.ProductDetails(
                            productId = it.id
                        )
                    )
                }
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