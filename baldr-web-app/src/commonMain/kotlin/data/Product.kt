package data

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4


data class Product(
    val name: String,
    val tagline: String?,
    val price: Double,
    val currency: String,
    val description: String? = null,
    val color: Int? = null,

    val media: List<Media>,
    val attributes: List<Attribute> = emptyList(),

    val relatedProducts: List<Uuid> = emptyList(),
    val colorVariants: List<ColorVariant> = emptyList(),

    val id: Uuid = uuid4(),
    val orderId: Double = 0.0,
    val archived: Boolean = false
)