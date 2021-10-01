package data

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.serialization.Serializable
import rest.UuidSerializer

@Serializable
data class Media(
    @Serializable(with = UuidSerializer::class)
    val id: Uuid = uuid4(),
    val url: String,
    val type: MediaType,
    val alt: String? = null,
    @Serializable(with = UuidSerializer::class)
    val productId: Uuid,
    val orderNum: Double
)