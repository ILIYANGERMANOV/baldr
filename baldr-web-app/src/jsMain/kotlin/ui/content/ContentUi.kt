package ui.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import components.MediaView
import core.Route
import core.observeAsState
import data.Media
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.url.URL
import ui.home.ProductsRow
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
        val productMedia by logic.productMedia.observeAsState()

        ContentTextInput(
            title = "Name",
            value = name,
            onValueChange = {
                logic.name.value = it
            }
        )

        ProductsRow {
            for (media in productMedia) {
                ContentMedia(
                    media = media,
                    onAdd = logic::addMedia,
                    onChanged = logic::updateMedia,
                    onDelete = logic::removeMedia
                )
            }

            ContentMedia(
                media = null,
                onAdd = logic::addMedia,
                onChanged = logic::updateMedia,
                onDelete = logic::removeMedia
            )
        }

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
                    coroutineScope.launch {
                        logic.submit()
                    }
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

@Composable
private fun ContentMedia(
    media: Media?,
    onChanged: (Media) -> Unit,
    onAdd: (url: String) -> Unit,
    onDelete: (Media) -> Unit
) {
    Div({
        style {
            backgroundColor(Color("#f4f4f4"))
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            alignContent(AlignContent.Center)
            justifyContent(JustifyContent.Start)
            marginLeft(16.px)
            marginRight(16.px)
        }
    }) {
        if (media != null) {
            MediaView(
                media = media,
                width = 420,
                height = 420
            )
        } else {
            H4 {
                Text("Add new media")
            }
        }

        var url by remember(media) {
            mutableStateOf("")
        }

        TextInput(
            value = url
        ) {
            onInput {
                url = it.value
            }
        }

        Button(
            attrs = {
                onClick {
                    if (media != null) {
                        onChanged(
                            media.copy(
                                url = url
                            )
                        )
                    } else {
                        onAdd(url)
                    }
                }
            }
        ) {
            Text(if (media != null) "Update" else "Add")
        }
    }
}