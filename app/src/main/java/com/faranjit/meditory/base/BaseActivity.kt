package com.faranjit.meditory.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.ViewDataBinding
import com.faranjit.meditory.R

/**
 * Created by Bulent Turkmen on 24.08.2021.
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: VM

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

    protected fun showLoading() {
        progressDialog?.show()
    }

    protected fun hideLoading() {
        progressDialog?.dismiss()
    }

    fun showDialog(dialogModel: DialogModel) {
        dialogModel.run {
            val builder = AlertDialog.Builder(this@BaseActivity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton.text) { dialog, _ ->
                    positiveButton.task?.invoke() ?: dialog.dismiss()
                }

            negativeButton?.let {
                builder.setNegativeButton(it.text) { dialog, _ ->
                    it.task?.invoke() ?: dialog.dismiss()
                }
            }

            builder.create()
        }.show()
    }
}