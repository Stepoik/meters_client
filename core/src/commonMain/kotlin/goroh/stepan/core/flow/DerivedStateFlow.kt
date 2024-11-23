package goroh.stepan.core.flow

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.combine as coroutinesCombine

class DerivedStateFlow<T>(
    private val getValue: () -> T,
    private val flow: Flow<T>
) : StateFlow<T> {

    override val replayCache: List<T>
        get() = listOf(value)

    override val value: T
        get() = getValue()

    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        coroutineScope { flow.distinctUntilChanged().stateIn(this).collect(collector) }
    }
}

fun <T1, R> StateFlow<T1>.mapState(transform: (a: T1) -> R): StateFlow<R> {
    return DerivedStateFlow(
        getValue = { transform(this.value) },
        flow = this.map { a -> transform(a) }
    )
}

inline fun <T1, T2, R> combine(
    stateFlow1: StateFlow<T1>,
    stateFlow2: StateFlow<T2>,
    crossinline transform: (a: T1, b: T2) -> R
): StateFlow<R> {
    return DerivedStateFlow(
        getValue = { transform(stateFlow1.value, stateFlow2.value) },
        flow = coroutinesCombine(stateFlow1, stateFlow2) { a, b ->
            transform(a, b)
        }
    )
}