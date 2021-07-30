package com.mehmetkaya.relaxingtime.main

import com.mehmetkaya.relaxingtime.ui.main.MainViewModel
import com.mehmetkaya.relaxingtime.utils.CoroutineTestRule
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule

class MainViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel()
    }
}
