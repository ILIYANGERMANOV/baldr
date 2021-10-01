package rest.request

import data.Product

data class UploadProductRequest(
    val product: Product,
    val password: String
)