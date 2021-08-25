package com.faranjit.meditory.features.login.presentation

import android.os.Bundle
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
        binding.btnSignin.setOnClickListener {
            homeViewModel.signIn()
        }

        observeLiveData(homeViewModel.signinSuccessLiveData) {

        }
    }

    override fun provideViewModel() = homeViewModel

    override fun provideBinding() = binding

    override fun bindViewModel(binding: ActivityLoginBinding) {
        binding.viewModel = homeViewModel
    }
}