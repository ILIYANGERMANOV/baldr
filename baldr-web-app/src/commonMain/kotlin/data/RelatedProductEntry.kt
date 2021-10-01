package data

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

data class RelatedProductEntry(
    val id: Uuid = uuid4(),
    val productId: Uuid,
    val relatedProductId: Uuid,
    val colorVariant: Int? = null,
    val orderNum: Double
)