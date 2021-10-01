package rest.request

import data.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val product: Product
)