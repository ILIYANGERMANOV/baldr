package core

import androidx.compose.runtime.*

open class LiveData<T: Any>(
    initial: T
) {
    var value: T = initial
        set(value) {
            field = value
            observer?.onValueChanged(field)
        }

    private var observer: ValueObserver<T>? = null

    fun subscribe(observer: ValueObserver<T>) {
        this.observer = observer
    }

    fun unsubscribe() {
        observer = null
    }
}

interface ValueObserver<T: Any> {
    fun onValueChanged(newValue: T)
}

@Composable
fun <T : Any> LiveData<T>.observeAsState(): State<T> {
    val state = remember(this) { mutableStateOf(value) }

    DisposableEffect(this) {
        val observer = object : ValueObserver<T> {
            override fun onValueChanged(newValue: T) {
                state.value = newValue
            }
        }


        subscribe(observer)

        onDispose {
            unsubscribe()
        }
    }

    return state
}