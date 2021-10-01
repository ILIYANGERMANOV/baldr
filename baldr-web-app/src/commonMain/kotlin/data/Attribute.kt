package data

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4


data class Attribute(
    val id: Uuid = uuid4(),
    val key: String,
    val value: String,
    val orderNum: Double,
    val productId: Uuid
)