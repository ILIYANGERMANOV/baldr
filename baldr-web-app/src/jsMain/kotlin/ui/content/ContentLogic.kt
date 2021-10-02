package ui.content

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import core.LiveData
import core.Route
import data.Media
import data.MediaType
import data.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import logic.restClient
import rest.RestClient
import rest.request.DeleteProductRequest
import rest.request.UploadProductRequest
import util.log

class ContentLogic(
    private val restClient: RestClient = restClient()
) {
    val name = LiveData("")
    val tagline = LiveData("")
    val price = LiveData(0.0)
    val description = LiveData("")
    val productMedia = LiveData(listOf<Media>())

    lateinit var productId: Uuid

    fun start(coroutineScope: CoroutineScope, route: Route.Content) {
        productId = route.productId ?: uuid4()

        if (route.productId != null) {
            coroutineScope.launch {
                val product = restClient.getProduct(productId = productId).product

                name.value = product.name
                tagline.value = product.tagline ?: ""
                price.value = product.price
                description.value = product.description ?: ""
                productMedia.value = product.media
            }
        }
    }

    fun addMedia(url: String, type: MediaType) {
        log("Adding media...")
        val finalUrl = when (type) {
            MediaType.IMAGE -> url
            MediaType.YOUTUBE_VIDEO -> {
                //Default: https://youtu.be/qpNlFPiokIs
                //Correct: https://www.youtube.com/embed/qpNlFPiokIs
                "https://www.youtube.com/embed/${url}"
            }
        }

        productMedia.value = productMedia.value.plus(
            Media(
                productId = productId,
                url = finalUrl,
                type = type,
                orderNum = productMedia.value.maxOfOrNull { it.orderNum }?.plus(1) ?: 0.0
            )
        )
        log("Media added: ${productMedia.value.lastOrNull()}")
    }

    fun updateMedia(updatedMedia: Media) {
        productMedia.value = productMedia.value
            .filter { it.id != updatedMedia.id }
            .plus(updatedMedia)
            .sortedBy { it.orderNum }
    }

    fun removeMedia(media: Media) {
        productMedia.value = productMedia.value
            .filter { it.id != media.id }
    }

    suspend fun submit() {
        restClient().uploadProduct(
            UploadProductRequest(
                product = Product(
                    id = productId,
                    name = name.value,
                    tagline = tagline.value,
                    price = price.value,
                    currency = "BGN",
                    media = productMedia.value,
                    description = description.value,
                    orderNum = 0.0
                ),
                password = RestClient.BALDR_PASSWORD
            )
        )

        clear()
    }

    suspend fun deleteProduct() {
        restClient().deleteProduct(
            DeleteProductRequest(
                productId = productId,
                password = RestClient.BALDR_PASSWORD
            )
        )

        clear()
    }

    private fun clear() {
        productId = uuid4()
        name.value = ""
        tagline.value = ""
        price.value = 0.0
        description.value = ""
        productMedia.value = emptyList()
    }
}