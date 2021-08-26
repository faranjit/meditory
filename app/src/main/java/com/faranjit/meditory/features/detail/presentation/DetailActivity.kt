package com.faranjit.meditory.features.detail.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.faranjit.meditory.R
import com.faranjit.meditory.base.BaseActivity
import com.faranjit.meditory.base.viewBinding
import com.faranjit.meditory.databinding.ActivityDetailBinding
import com.faranjit.meditory.observeLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class DetailActivity : BaseActivity<DetailViewModel, ActivityDetailBinding>() {

    companion object {
        const val KEY_DETAIL = "detail_model"

        fun newIntent(context: Context, detailModel: DetailModel) =
            Intent(context, DetailActivity::class.java).apply {
                val bundle = Bundle().also {
                    putExtra(KEY_DETAIL, detailModel)
                }

                putExtras(bundle)
            }
    }

    private val binding by viewBinding(ActivityDetailBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        initUI()
        observe()
        viewModel.parseIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun provideViewModel() = viewModel<DetailViewModel>().value

    override fun provideBinding() = binding

    override fun bindViewModel(binding: ActivityDetailBinding) {
        binding.viewModel = viewModel
    }

    override fun onDestroy() {
        viewModel.clearMediaPlayer()
        super.onDestroy()
    }

    private fun initUI() {
        binding.imgPlay.setOnClickListener {
            viewModel.playOrPauseAudio()
        }
    }

    private fun observe() {
        viewModel.run {
            observeLiveData(detailTypeLiveData) {
                title = if (it == DetailType.MEDITATION) {
                    getString(R.string.meditation_detail)
                } else {
                    getString(R.string.story_detail)
                }
            }
        }
    }
}