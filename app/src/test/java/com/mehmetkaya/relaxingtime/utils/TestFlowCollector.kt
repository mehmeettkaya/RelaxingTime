package com.mehmetkaya.relaxingtime.utils

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowCollector
import org.assertj.core.api.Assertions.assertThat

class TestFlowCollector<T>(var job: Job?) : FlowCollector<T> {

    private val emittedItems = ArrayDeque<T>()

    val itemCount: Int
        get() = emittedItems.size

    override suspend fun emit(value: T) {
        emittedItems.add(value)
    }

    fun expectItem(): T = emittedItems.removeFirst()

    fun containsItem(item: T) {
        assertThat(emittedItems).contains(item)
    }

    fun doesNotContainItem(item: T) {
        assertThat(emittedItems).doesNotContain(item)
    }

    fun verifyNoInteractions() {
        assertThat(emittedItems).isEmpty()
    }

    fun verify(block: TestFlowCollector<T>.() -> Unit) {
        block(this)
        job?.cancel(CancellationException("Finishing the job of ${javaClass.simpleName}"))
    }
}
