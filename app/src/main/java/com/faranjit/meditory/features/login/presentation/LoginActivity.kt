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

    private val homeViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        observe()
    }

    override fun provideViewModel() = homeViewModel

    override fun provideBinding() = binding

    override fun bindViewModel(binding: ActivityLoginBinding) {
        binding.viewModel = homeViewModel
    }

    private fun initUI() {
        binding.run {
            imgVisibility.setOnClickListener {
                homeViewModel.showOrHidePassword()
            }

            btnSignin.setOnClickListener {
                homeViewModel.signIn()
            }
        }
    }

    private fun observe() {
        observeLiveData(homeViewModel.passwordVisibilityLiveData) {
            binding.edtPassword.transformationMethod = it
        }

        observeLiveData(homeViewModel.signinSuccessLiveData) {
            if (it) {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
            }
        }
    }
}