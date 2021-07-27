package com.mehmetkaya.relaxingtime

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.mehmetkaya.relaxingtime.exts.collectIn
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach

fun <V : StatefulViewModel<S, *>, S> ComponentActivity.withUiState(
    viewModel: V,
    onStateChanged: suspend (S) -> Unit
) = withUiState(viewModel, this, onStateChanged)

fun <V : StatefulViewModel<S, *>, S> Fragment.withUiState(
    viewModel: V,
    onStateChanged: suspend (S) -> Unit
) = withUiState(viewModel, viewLifecycleOwner, onStateChanged)

private fun <V : StatefulViewModel<S, *>, S> withUiState(
    viewModel: V,
    lifecycleOwner: LifecycleOwner,
    onStateChanged: suspend (S) -> Unit
) {
    viewModel.stateFlow
        .map { it.uiState }
        .distinctUntilChanged()
        .onEach(onStateChanged)
        .collectIn(lifecycleOwner)
}

fun <V : StatefulViewModel<*, E>, E> ComponentActivity.withProgress(
    viewModel: V,
    onProgressEvent: suspend (Boolean) -> Unit
) = withProgress(viewModel, this, onProgressEvent)

fun <V : StatefulViewModel<*, E>, E> Fragment.withProgress(
    viewModel: V,
    onProgressEvent: suspend (Boolean) -> Unit
) = withProgress(viewModel, viewLifecycleOwner, onProgressEvent)

private fun <V : StatefulViewModel<*, *>> withProgress(
    viewModel: V,
    lifecycleOwner: LifecycleOwner,
    onProgressEvent: suspend (Boolean) -> Unit
) {
    viewModel.stateFlow
        .map { it.progress }
        .distinctUntilChanged()
        .onEach(onProgressEvent)
        .collectIn(lifecycleOwner)
}

fun <V : StatefulViewModel<*, E>, E> ComponentActivity.withError(
    viewModel: V,
    onErrorEvent: suspend (Exception) -> Unit
) = withError(viewModel, this, onErrorEvent)

fun <V : StatefulViewModel<*, E>, E> Fragment.withError(
    viewModel: V,
    onErrorEvent: suspend (Exception) -> Unit
) = withError(viewModel, viewLifecycleOwner, onErrorEvent)

private fun <V : StatefulViewModel<*, *>> withError(
    viewModel: V,
    lifecycleOwner: LifecycleOwner,
    onErrorEvent: suspend (Exception) -> Unit
) {
    viewModel.stateFlow
        .mapNotNull { it.error }
        .onEach(onErrorEvent)
        .collectIn(lifecycleOwner)
}

fun <V : StatefulViewModel<*, E>, E> ComponentActivity.withEvent(
    viewModel: V,
    onEventPushed: suspend (E) -> Unit
) = withEvent(viewModel, this, onEventPushed)

fun <V : StatefulViewModel<*, E>, E> Fragment.withEvent(
    viewModel: V,
    onEventPushed: suspend (E) -> Unit
) = withEvent(viewModel, viewLifecycleOwner, onEventPushed)

private fun <V : StatefulViewModel<*, E>, E> withEvent(
    viewModel: V,
    lifecycleOwner: LifecycleOwner,
    onEventPushed: suspend (E) -> Unit
) {
    viewModel.eventFlow
        .onEach(onEventPushed)
        .collectIn(lifecycleOwner)
}
