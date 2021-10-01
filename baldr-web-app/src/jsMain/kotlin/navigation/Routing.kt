package navigation

import androidx.compose.runtime.*
import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidFrom
import kotlinx.browser.window
import util.log

object Routing {
    private var routePath: String by mutableStateOf("", neverEqualPolicy())

    init {
        window.onpopstate = {
            log("onpopstate: path = ${window.location.pathname}")
            updateRoute(window.location.pathname)
        }
        window.onhashchange = {
            log("onhashchange: path = ${window.location.pathname}")
            updateRoute(window.location.pathname)
        }
    }

    @Composable
    fun init(Content: @Composable (routePath: String) -> Unit) {
        Content(routePath)
    }

    fun navigate(destination: Route) {
        val destinationPath = destination.path()
        window.history.pushState(null, "", destinationPath)
        updateRoute(destinationPath)
    }

    private fun updateRoute(path: String) {
        routePath = path
    }
}

sealed class Route {
    abstract fun path(): String

    object Home : Route() {
        override fun path() = ""
    }

    data class ProductDetails(val productId: Uuid) : Route() {
        override fun path() = "/product/${productId}"
    }

    companion object {
        fun parse(path: String): Route {
            return when {
                path.startsWith("/product/") -> {
                    val productId = path.split("/")
                        .getOrNull(2)
                        ?.takeIf { it.isNotBlank() } ?: return Home
                    ProductDetails(uuidFrom(productId))
                }
                else -> Home
            }
        }
    }
}