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
        observeViewModel()
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

    private fun observeViewModel() = with(viewModel) {
        withUiState(this) { state ->
            binding.continueButton.isEnabled = state.isValid
        }

        withEvent(this) { event ->
            when (event) {
                NavigateToHome -> navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        hideKeyboard()

        startActivity<MainActivity> {
            withNoAnimation()
            withNoHistory()
        }
    }
}
