package com.mehmetkaya.relaxingtime.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mehmetkaya.core.withEvent
import com.mehmetkaya.core.withUiState
import com.mehmetkaya.relaxingtime.databinding.ActivityLoginBinding
import com.mehmetkaya.relaxingtime.ui.login.LoginViewModel.LoginEvent.NavigateToHome
import com.mehmetkaya.relaxingtime.ui.main.MainActivity
import com.mehmetkaya.utils.exts.*
import com.mehmetkaya.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()

        observeState()
        observeEvent()
    }

    private fun initView() = with(binding) {
        viewModel.onFormChange(
            userNameEditText.textChanges(),
            passwordEditText.textChanges()
        )

        continueButton.setOnClickListener {
            val userName = userNameEditText.text.toString()
            viewModel.continueButtonClicked(userName)
        }
    }

    private fun observeState() = withUiState(viewModel) { state ->
        binding.continueButton.isEnabled = state.isValid
    }

    private fun observeEvent() = withEvent(viewModel) { event ->
        when (event) {
            NavigateToHome -> {
                hideKeyboard()
                startActivity<MainActivity> {
                    withNoAnimation()
                    withNoHistory()
                }
            }
        }
    }
}
