package data


data class Product(
    val title: String,
    val subtitle: String?,
    val description: String?,
    val price: Double,
    val currency: String,
    val media: List<Media>,

    val id: String,
    val orderId: Double
)