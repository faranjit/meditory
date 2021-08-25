package com.faranjit.meditory.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.ViewDataBinding
import com.faranjit.meditory.R

/**
 * Created by Bulent Turkmen on 24.08.2021.
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    protected var viewModel: VM? = null

    protected lateinit var viewDataBinding: VB

    private val progressDialog: AppCompatDialog? by lazy {
        AppCompatDialog(this, R.style.ProgressDialog).apply {
            setContentView(R.layout.dialog_loading)
            setCancelable(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()
        viewDataBinding = provideBinding()
        bindViewModel(viewDataBinding)
        viewModel?.loadingLiveData?.observe(this, {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    abstract fun provideViewModel(): VM

    abstract fun provideBinding(): VB

    abstract fun bindViewModel(binding: VB)

    fun showLoading() {
        progressDialog?.show()
    }

    fun hideLoading() {
        progressDialog?.dismiss()
    }
}