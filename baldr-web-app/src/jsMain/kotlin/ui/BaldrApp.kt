package ui

import core.Route
import core.Routing
import kotlinx.browser.window
import org.jetbrains.compose.web.renderComposable
import ui.home.Home
import ui.product.ProductDetails
import util.log


fun main() {
    renderComposable(rootElementId = "root") {
        Routing.init { statePath ->
            val routePath = window.location.pathname
            val route = Route.parse(routePath)
            //NOTE: Must log state path so the compose function is updated
            log("Routing.init: '$routePath' (state path = $statePath) translated to $route")
            when (route) {
                Route.Home -> Home()
                is Route.ProductDetails -> ProductDetails(route)
            }
        }
    }
}


