package rest.request

import com.benasher44.uuid.Uuid

data class DeleteProductRequest(
    val productId: Uuid,
    val password: String
)