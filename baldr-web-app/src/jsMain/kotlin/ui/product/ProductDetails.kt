package ui.product

import androidx.compose.runtime.Composable
import core.Route
import org.jetbrains.compose.web.dom.Text

@Composable
fun ProductDetails(productDetails: Route.ProductDetails) {
    Text("Product details for ${productDetails.productId}")
}