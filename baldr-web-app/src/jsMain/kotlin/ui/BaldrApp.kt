package ui

import core.Route
import core.Routing
import org.jetbrains.compose.web.renderComposable
import ui.home.Home
import ui.product.ProductDetails


fun main() {
    renderComposable(rootElementId = "root") {
        Routing.init {
            when (it) {
                Route.Home -> Home()
                is Route.ProductDetails -> ProductDetails(it)
            }
        }
    }
}


