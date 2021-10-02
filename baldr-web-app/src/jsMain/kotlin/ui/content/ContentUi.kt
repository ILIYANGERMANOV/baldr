package ui.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import core.Route
import core.observeAsState
import org.jetbrains.compose.web.dom.*
import util.onStart

@Composable
fun ContentUi(route: Route.Content) {
    val logic = remember(route) {
        ContentLogic()
    }

    onStart {
        logic.start(route)
    }

    Div {
        H1 {
            Text("Baldr Content")
        }

        val name by logic.name.observeAsState()
        val tagline by logic.tagline.observeAsState()
        val price by logic.price.observeAsState()
        val description by logic.description.observeAsState()

        ContentTextInput(
            title = "Name",
            value = name,
            onValueChange = {
                logic.name.value = it
            }
        )

        ContentTextInput(
            title = "Tagline",
            value = tagline,
            onValueChange = {
                logic.tagline.value = it
            }
        )

        ContentNumberInput(
            title = "Price",
            value = price,
            onValueChange = {
                logic.price.value = it
            }
        )

        ContentTextInput(
            title = "Description",
            value = description,
            onValueChange = {
                logic.description.value = it
            }
        )

        Br()

        val coroutineScope = rememberCoroutineScope()
        Button(
            attrs = {
                onClick {
                    //TODO: Add product
                }
            }
        ) {
            Text("Submit")
        }
    }
}

@Composable
private fun ContentTextInput(
    title: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Div {
        H3 {
            Text(title)
        }
        TextArea {
            onInput {
                onValueChange(it.value)
            }
            value(value)
        }
    }
}

@Composable
private fun ContentNumberInput(
    title: String,
    value: Double,
    onValueChange: (Double) -> Unit
) {
    Div {
        H3 {
            Text(title)
        }
        NumberInput(
            value = value
        ) {
            onInput {
                (it.value as? Double)?.let(onValueChange)
            }
        }
    }
}