package rest.request

import data.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val products: List<Product>
)