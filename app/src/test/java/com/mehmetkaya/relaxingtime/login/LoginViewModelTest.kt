package com.mehmetkaya.relaxingtime.login

import com.mehmetkaya.relaxingtime.ui.login.LoginViewModel
import com.mehmetkaya.relaxingtime.ui.login.LoginViewModel.LoginEvent.NavigateToHome
import com.mehmetkaya.relaxingtime.utils.CoroutineTestRule
import com.mehmetkaya.relaxingtime.utils.ext.testIn
import com.mehmetkaya.utils.preference.StringPreference
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var userNamePref: StringPreference

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        loginViewModel = LoginViewModel(userNamePref)
    }

    @Test
    fun `Save username and navigate to home when continue button is clicked`() {
        coroutineTestRule.runBlockingTest {
            val userName = "userName"
            val eventCollector = loginViewModel.eventFlow.testIn(this)

            loginViewModel.continueButtonClicked(userName)

            verify { userNamePref.set(userName) }
            eventCollector.verify { assertThat(expectItem()).isEqualTo(NavigateToHome) }
        }
    }
}
