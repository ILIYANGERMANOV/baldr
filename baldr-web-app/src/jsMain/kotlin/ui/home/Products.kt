package ui.home

import androidx.compose.runtime.Composable
import css.Ivy
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Composable
fun Products(
    Content: @Composable () -> Unit
) {
    Div({
        style {
            width(100.percent)
            backgroundColor(Ivy)
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
            alignContent(AlignContent.Center)
            justifyContent(JustifyContent.Center)
        }
    }) {
        Content()
    }
}