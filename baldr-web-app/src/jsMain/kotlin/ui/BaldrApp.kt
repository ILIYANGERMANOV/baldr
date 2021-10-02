package ui

import kotlinx.browser.window
import core.Route
import core.Routing
import org.jetbrains.compose.web.renderComposable
import ui.content.ContentUi
import ui.home.HomeUi
import ui.product.ProductUi
import util.log


fun main() {
    renderComposable(rootElementId = "root") {
        Routing.init { statePath ->
            val routePath = window.location.pathname
            val route = Route.parse(routePath)
            //NOTE: Must log state path so the compose function is updated
            log("Routing.init: '$routePath' (state path = $statePath) translated to $route")
            when (route) {
                is Route.Home -> HomeUi()
                is Route.ProductDetails -> ProductUi(route)
                is Route.Content -> ContentUi(route)
            }
        }
    }
}


