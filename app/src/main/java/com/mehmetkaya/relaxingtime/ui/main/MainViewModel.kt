package com.mehmetkaya.relaxingtime.ui.main

import com.mehmetkaya.core.NoEvent
import com.mehmetkaya.core.StatefulViewModel
import com.mehmetkaya.core.UiState
import com.mehmetkaya.relaxingtime.ui.main.MainViewModel.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : StatefulViewModel<MainState, NoEvent>(MainState) {

    object MainState : UiState
}
