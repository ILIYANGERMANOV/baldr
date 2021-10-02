package ui.product

import androidx.compose.runtime.*
import components.MediaView
import core.Route
import data.Product
import kotlinx.coroutines.launch
import logic.restClient
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import ui.home.ProductsRow
import util.onStart

@Composable
fun ProductUi(route: Route.ProductDetails) {

    var product: Product? by remember {
        mutableStateOf(null)
    }

    val coroutineScope = rememberCoroutineScope()
    onStart {
        coroutineScope.launch {
            product = restClient().getProduct(productId = route.productId).product
        }
    }

    if (product != null) {
        ProductDetails(product!!)
    }
}

@Composable
private fun ProductDetails(product: Product) {
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
        H1 {
            Text(product.name)
        }

        ProductsRow {
            for (media in product.media) {
                MediaView(
                    media = media,
                    width = 640,
                    height = 640
                )
            }
        }

        Div({
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                alignContent(AlignContent.Center)
                justifyContent(JustifyContent.SpaceAround)
            }
        }) {
            product.tagline?.let {
                P {
                    Text(it)
                }
            }

            //Price
            H2 {
                Text("${product.currency} ${product.price}")
            }
        }

        //Description
        product.description?.let {
            P {
                Text(it)
            }
        }
    }
}