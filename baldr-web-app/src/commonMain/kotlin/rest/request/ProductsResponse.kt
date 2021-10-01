package rest.request

import data.Product

data class ProductsResponse(
    val products: List<Product>
)