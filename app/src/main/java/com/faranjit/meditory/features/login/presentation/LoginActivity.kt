package com.faranjit.meditory.features.login.presentation

import android.os.Bundle
import com.faranjit.meditory.R
import com.faranjit.meditory.base.BaseActivity
import com.faranjit.meditory.base.DialogButton
import com.faranjit.meditory.base.DialogModel
import com.faranjit.meditory.base.viewBinding
import com.faranjit.meditory.databinding.ActivityLoginBinding
import com.faranjit.meditory.features.home.presentation.HomeActivity
import com.faranjit.meditory.hideSoftInput
import com.faranjit.meditory.observeLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Bulent Turkmen on 24.08.2021.
 */
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        observe()
    }

    override fun provideViewModel() = viewModel<LoginViewModel>().value

    override fun provideBinding() = binding

    override fun bindViewModel(binding: ActivityLoginBinding) {
        binding.viewModel = viewModel
    }

    private fun initUI() {
        binding.run {
            imgVisibility.setOnClickListener {
                viewModel?.showOrHidePassword()
            }

            btnSignin.setOnClickListener {
                it.hideSoftInput()
                viewModel?.signIn()
            }
        }
    }

    private fun observe() {
        observeLiveData(viewModel.passwordVisibilityLiveData) {
            binding.edtPassword.transformationMethod = it
        }

        observeLiveData(viewModel.signinSuccessLiveData) {
            if (it) {
                startActivity(HomeActivity.newIntent(this))
                finish()
            } else {
                showDialog(
                    DialogModel(
                        getString(R.string.error),
                        getString(R.string.missing_credentials),
                        DialogButton(
                            getString(R.string.retry)
                        ),
                        DialogButton(getString(R.string.cancel)) {
                            finish()
                        }
                    )
                )
            }
        }
    }
}