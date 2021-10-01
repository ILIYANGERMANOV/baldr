package data

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.serialization.Serializable
import rest.UuidSerializer

@Serializable
data class RelatedProductEntry(
    @Serializable(with = UuidSerializer::class)
    val id: Uuid = uuid4(),
    @Serializable(with = UuidSerializer::class)
    val productId: Uuid,
    @Serializable(with = UuidSerializer::class)
    val relatedProductId: Uuid,
    val colorVariant: Int? = null,
    val orderNum: Double
)