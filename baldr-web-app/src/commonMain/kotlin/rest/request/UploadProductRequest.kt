package rest.request

import data.Product
import kotlinx.serialization.Serializable

@Serializable
data class UploadProductRequest(
    val product: Product,
    val password: String
)