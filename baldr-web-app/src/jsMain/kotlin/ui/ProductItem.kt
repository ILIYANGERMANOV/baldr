package ui

import androidx.compose.runtime.Composable
import components.MediaView
import data.Product
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun ProductItem(
    product: Product,
    onClick: (Product) -> Unit
) {
    Div({
        style {
            backgroundColor(Color("#f4f4f4"))
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            alignContent(AlignContent.Center)
            justifyContent(JustifyContent.Center)
            marginLeft(32.px)
            marginRight(32.px)
        }

        onClick {
            onClick(product)
        }
    }) {
        MediaView(
            media = product.media.first(),
            width = 420,
            height = 420
        )

        Div({
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                alignContent(AlignContent.Center)
                justifyContent(JustifyContent.SpaceAround)
            }
        }) {
            Div {
                H1 {
                    Text(product.name)
                }

                product.tagline?.let {
                    P {
                        Text(it)
                    }
                }
            }

            //Price
            H2 {
                Text("${product.currency} ${product.price}")
            }
        }

    }
}