package com.mehmetkaya.relaxingtime.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
@Suppress("Unused")
val testDispatchers = CoroutineDispatchers(
    TestCoroutineDispatcher(),
    TestCoroutineDispatcher(),
    TestCoroutineDispatcher()
)

data class CoroutineDispatchers(
    val io: CoroutineDispatcher = Dispatchers.IO,
    val default: CoroutineDispatcher = Dispatchers.Default,
    val main: CoroutineDispatcher = Dispatchers.Main
)
