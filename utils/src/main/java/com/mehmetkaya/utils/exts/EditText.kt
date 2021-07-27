package com.mehmetkaya.utils.exts

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@OptIn(ExperimentalCoroutinesApi::class)
fun EditText.textChanges(): Flow<String> = callbackFlow {
    offer(text.toString())
    val listener = addTextChangedListener { text -> offer(text.toString()) }
    awaitClose { removeTextChangedListener(listener) }
}
