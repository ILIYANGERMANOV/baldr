package core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.window

object Routing {
    private var routePath: String by mutableStateOf("")

    @Composable
    fun init(Content: @Composable (route: Route) -> Unit) {
        Content(Route.parse(routePath))
    }

    fun navigate(destination: Route) {
        val destinationPath = destination.path()
        window.history.pushState(null, "", destinationPath)
        routePath = destinationPath
    }
}

sealed class Route {
    abstract fun path(): String

    object Home : Route() {
        override fun path() = ""
    }

    data class ProductDetails(val productId: String) : Route() {
        override fun path() = "product/${productId}"
    }

    companion object {
        fun parse(path: String): Route {
            return when {
                path.contains("product/") -> {
                    val productId = path.split("/")
                        .getOrNull(1)
                        ?.takeIf { it.isNotBlank() } ?: return Home
                    ProductDetails(productId)
                }
                else -> Home
            }
        }
    }
}