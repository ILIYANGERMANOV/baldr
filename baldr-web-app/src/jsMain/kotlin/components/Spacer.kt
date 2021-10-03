package components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div

@Composable
fun Spacer(
    width: CSSNumeric? = null,
    height: CSSNumeric? = null
) {
    Div(
        attrs = {
            style {
                if (width != null) {
                    this.width(width)
                }

                if (height != null) {
                    this.height(height)
                }
            }
        }
    )
}