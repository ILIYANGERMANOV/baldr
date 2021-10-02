package util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

fun log(message: String) {
    console.log(message)
}

@Composable
fun onStart(sideEffect: () -> Unit) {
    LaunchedEffect(true) {
        sideEffect()
    }
}