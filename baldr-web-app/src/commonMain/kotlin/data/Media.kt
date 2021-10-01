package data

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4


data class Media(
    val id: Uuid = uuid4(),
    val url: String,
    val type: MediaType,
    val alt: String? = null,
    val productId: Uuid,
    val orderNum: Double
)