package components

import androidx.compose.runtime.Composable
import data.Media
import data.MediaType
import org.jetbrains.compose.web.dom.Img

@Composable
fun MediaView(media: Media) {
    when (media.type) {
        MediaType.IMAGE -> {
            Img(
                src = media.url,
                alt = media.alt ?: "img"
            )
        }
        MediaType.VIDEO -> {
            //TODO: Implement
        }
    }
}