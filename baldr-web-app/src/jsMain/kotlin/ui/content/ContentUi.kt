package ui.content

import androidx.compose.runtime.*
import components.*
import core.Route
import core.Routing
import core.observeAsState
import data.Media
import data.MediaType
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import ui.home.ProductsRow
import util.onStart

@Composable
fun ContentUi(route: Route.Content) {
    val logic = remember(route) {
        ContentLogic()
    }

    val coroutineScope = rememberCoroutineScope()
    onStart {
        logic.start(coroutineScope, route)
    }

    Div {
        Row {
            H1 {
                Text("Baldr Content")
            }

            Spacer(width = 16.px)

            Button(
                text = "Back to home"
            ) {
                Routing.navigate(Route.Home)
            }
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
                    onRemove = logic::removeMedia
                )
            }

            ContentMedia(
                media = null,
                onAdd = logic::addMedia,
                onChanged = logic::updateMedia,
                onRemove = logic::removeMedia
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

        CoroutineButton(
            text = "Submit",
            coroutineScope = coroutineScope
        ) {
            logic.submit()
        }

        if (route.productId != null) {
            Br()

            CoroutineButton(
                text = "Delete product",
                coroutineScope = coroutineScope
            ) {
                logic.deleteProduct()
            }
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
    onAdd: (url: String, MediaType) -> Unit,
    onRemove: (Media) -> Unit
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
            mutableStateOf(media?.url ?: "")
        }

        TextInput(
            value = url
        ) {
            onInput {
                url = it.value
            }
        }

        Button(
            text = if (media != null) "Update" else "Add Image",
        ) {
            if (media != null) {
                onChanged(
                    media.copy(
                        url = url
                    )
                )
            } else {
                onAdd(url, MediaType.IMAGE)
            }
        }

        if (media == null) {
            Button(
                text = "Add Video",
            ) {
                onAdd(url, MediaType.YOUTUBE_VIDEO)
            }
        }

        if (media != null) {
            Br()
            Br()

            Button(
                text = "Remove",
            ) {
                onRemove(media)
            }
        }
    }
}