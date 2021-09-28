package components

import androidx.compose.runtime.Composable
import data.Media
import data.MediaType
import org.jetbrains.compose.web.dom.Iframe
import org.jetbrains.compose.web.dom.Img

@Composable
fun MediaView(
    media: Media,
    width: Int = 320,
    height: Int = 320
) {
    when (media.type) {
        MediaType.IMAGE -> {
            Img(
                src = media.url,
                alt = media.alt ?: "img",
                attrs = {
                    attr("width", width.toString())
                    attr("height", width.toString())
                }
            )
        }
        MediaType.YOUTUBE_VIDEO -> {
            Iframe(
                attrs = {
                    attr("src", media.url)
                    attr("width", width.toString())
                    attr("height", width.toString())
                    attr("allow", "fullscreen;")
                }
            )
        }
    }
}