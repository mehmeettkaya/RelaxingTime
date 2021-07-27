package com.mehmetkaya.utils.exts

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.addRepeatingJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * A convenience wrapper around [addRepeatingJob] that simply calls [collect]
 * with [action]. Think of it as [kotlinx.coroutines.flow.launchIn], but for collecting.
 *
 * ```
 * uiStateFlow.collectIn(owner) { uiState ->
 *   updateUi(uiState)
 * }
 * ```
 */
fun <T> Flow<T>.collectIn(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend CoroutineScope.(T) -> Unit = {}
) = owner.addRepeatingJob(minActiveState) {
    collect { action(it) }
}
