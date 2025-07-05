package com.nicholas.rutherford.moments.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow

/**
 * Extension function that converts a [Flow] to a lifecycle-aware [State].
 *
 * This extension ensures that the flow is only collected when the lifecycle of the
 * provided [lifecycleOwner] is in the [Lifecycle.State.STARTED] state or higher,
 * preventing memory leaks and unnecessary updates when the composable is not in a valid state.
 *
 * @param lifecycleOwner The lifecycle owner (usually a composable or activity).
 * @param initialState The initial value of the state before any flow updates.
 *
 * @return A [State] that is lifecycle-aware and will only be collected when the lifecycle is active.
 */
@Composable
fun <T> Flow<T>.asLifecycleAwareState(lifecycleOwner: LifecycleOwner, initialState: T) =
    lifecycleAwareState(lifecycleOwner, this, initialState)

/**
 * Internal function that converts a [Flow] to a lifecycle-aware [State].
 *
 * This function uses [flowWithLifecycle] to ensure that the flow is collected only when the lifecycle
 * is in the [Lifecycle.State.STARTED] state or higher. It uses [collectAsState] to collect the flow's
 * values into a [State] object, which can be used within a composable.
 *
 * @param lifecycleOwner The lifecycle owner (usually a composable or activity).
 * @param flow The [Flow] that provides data updates.
 * @param initialState The initial value of the state before any flow updates.
 *
 * @return A [State] object that can be observed within a composable.
 */
@Composable
internal fun <T> lifecycleAwareState(
    lifecycleOwner: LifecycleOwner,
    flow: Flow<T>,
    initialState: T
): State<T> {
    // Remember the flow and lifecycleOwner, ensuring the state flow is lifecycle-aware.
    val lifecycleAwareStateFlow = remember(flow, lifecycleOwner) {
        flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    // Collect the flow's values as a state and return it.
    return lifecycleAwareStateFlow.collectAsState(initialState)
}
