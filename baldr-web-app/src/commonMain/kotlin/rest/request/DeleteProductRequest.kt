package rest.request

import com.benasher44.uuid.Uuid
import kotlinx.serialization.Serializable
import rest.UuidSerializer

@Serializable
data class DeleteProductRequest(
    @Serializable(with = UuidSerializer::class)
    val productId: Uuid,
    val password: String
)