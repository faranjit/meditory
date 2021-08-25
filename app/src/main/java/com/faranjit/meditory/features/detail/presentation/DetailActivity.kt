package com.faranjit.meditory.features.detail.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.faranjit.feedbacklist.base.viewBinding
import com.faranjit.meditory.R
import com.faranjit.meditory.base.BaseActivity
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

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        initUI()
        observe()
        detailViewModel.parseIntent(intent)
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

    override fun provideViewModel() = detailViewModel

    override fun provideBinding() = binding

    override fun bindViewModel(binding: ActivityDetailBinding) {
        binding.viewModel = detailViewModel
    }

    override fun onDestroy() {
        detailViewModel.clearMediaPlayer()
        super.onDestroy()
    }

    private fun initUI() {
        binding.run {
            imgPlay.setOnClickListener {
                detailViewModel.playOrPauseAudio()
            }
        }
    }

    private fun observe() {
        detailViewModel.run {
            observeLiveData(detailTypeLiveData) {
                if (it == DetailType.MEDITATION) {
                    title = getString(R.string.meditation_detail)
                } else {
                    title = getString(R.string.story_detail)
                }
            }
        }
    }
}