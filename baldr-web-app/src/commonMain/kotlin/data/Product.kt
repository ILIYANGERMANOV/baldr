package data


data class Product(
    val name: String,
    val tagline: String?,
    val description: String? = null,
    val price: Double,
    val currency: String,
    val media: List<Media>,

    val id: String,
    val orderId: Double
)