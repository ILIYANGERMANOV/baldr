package data

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.serialization.Serializable
import rest.UuidSerializer

@Serializable
data class Attribute(
    @Serializable(with = UuidSerializer::class)
    val id: Uuid = uuid4(),
    val key: String,
    val value: String,
    val orderNum: Double,
    @Serializable(with = UuidSerializer::class)
    val productId: Uuid
)