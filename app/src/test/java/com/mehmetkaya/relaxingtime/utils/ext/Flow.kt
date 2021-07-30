package com.mehmetkaya.relaxingtime.utils.ext

import com.mehmetkaya.relaxingtime.utils.TestFlowCollector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope

@OptIn(InternalCoroutinesApi::class, ExperimentalCoroutinesApi::class)
suspend fun <T> Flow<T>.testIn(
    testCoroutineScope: TestCoroutineScope
): TestFlowCollector<T> = with(testCoroutineScope) {
    val collector = TestFlowCollector<T>(null)
    collector.job = launch { this@testIn.collect(collector) }
    return collector
}
