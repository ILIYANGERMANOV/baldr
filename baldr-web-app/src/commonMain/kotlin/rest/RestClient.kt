package rest

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidFrom
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import rest.request.DeleteProductRequest
import rest.request.ProductResponse
import rest.request.ProductsResponse
import rest.request.UploadProductRequest

class RestClient(
    engine: HttpClientEngineFactory<HttpClientEngineConfig>,
) {
    companion object {
        const val API_URL = "https://ivy-apps.com"

        const val BALDR_PASSWORD = "magazinTopMax112$"
    }

    private val client: HttpClient = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }

        this.developmentMode = true
    }

    suspend fun getProducts(): ProductsResponse {
        return client.get("${API_URL}/baldr/products/all") {
            accept(ContentType.Application.Json)
        }
    }

    suspend fun getProduct(productId: Uuid): ProductResponse {
        return client.get("${API_URL}/baldr/products/single") {
            accept(ContentType.Application.Json)
            parameter("productId", productId.toString())
        }
    }

    suspend fun uploadProduct(request: UploadProductRequest) {
        val response: HttpResponse = client.post("${API_URL}/baldr/products/upload") {
            contentType(ContentType.Application.Json)
            body = request
        }
    }

    suspend fun deleteProduct(request: DeleteProductRequest) {
        val response: HttpResponse = client.post("${API_URL}/baldr/products/delete") {
            contentType(ContentType.Application.Json)
            body = request
        }
    }
}

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Uuid::class)
object UuidSerializer : KSerializer<Uuid> {
    override val descriptor = PrimitiveSerialDescriptor("Uuid", PrimitiveKind.STRING)


    override fun serialize(encoder: Encoder, value: Uuid) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Uuid {
        return uuidFrom(decoder.decodeString())
    }
}