package com.faranjit.meditory.features.login.presentation

import android.os.Bundle
import android.widget.Toast
import com.faranjit.feedbacklist.base.viewBinding
import com.faranjit.meditory.base.BaseActivity
import com.faranjit.meditory.databinding.ActivityLoginBinding
import com.faranjit.meditory.observeLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Bulent Turkmen on 24.08.2021.
 */
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        observe()
    }

    override fun provideViewModel() = loginViewModel

    override fun provideBinding() = binding

    override fun bindViewModel(binding: ActivityLoginBinding) {
        binding.viewModel = loginViewModel
    }

    private fun initUI() {
        binding.run {
            imgVisibility.setOnClickListener {
                loginViewModel.showOrHidePassword()
            }

            btnSignin.setOnClickListener {
                loginViewModel.signIn()
            }
        }
    }

    private fun observe() {
        observeLiveData(loginViewModel.passwordVisibilityLiveData) {
            binding.edtPassword.transformationMethod = it
        }

        observeLiveData(loginViewModel.signinSuccessLiveData) {
            if (it) {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
            }
        }
    }
}