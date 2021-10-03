package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.Text

@Composable
fun CoroutineButton(
    text: String,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onClick: suspend () -> Unit,
) {
    Button(text = text) {
        coroutineScope.launch {
            onClick()
        }
    }
}

@Composable
fun Button(
    text: String,
    onClick: () -> Unit
) {
    org.jetbrains.compose.web.dom.Button(
        attrs = {
            onClick {
                onClick()
            }
        }
    ) {
        Text(text)
    }
}