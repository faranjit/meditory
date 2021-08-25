package com.faranjit.meditory.features.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.faranjit.feedbacklist.base.viewBinding
import com.faranjit.meditory.base.BaseActivity
import com.faranjit.meditory.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

    private val binding by viewBinding(ActivityHomeBinding::inflate)

    private val homeViewModel: HomeViewModel by viewModel()

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
    }

    override fun provideViewModel() = homeViewModel

    override fun provideBinding() = binding

    override fun bindViewModel(binding: ActivityHomeBinding) {
        binding.viewModel = homeViewModel
    }

    private fun initUI() {
        homeViewModel.getHomeData()
    }

    private fun observe() {

    }
}