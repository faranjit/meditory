package com.faranjit.meditory.features.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.faranjit.meditory.R
import com.faranjit.meditory.base.*
import com.faranjit.meditory.databinding.ActivityHomeBinding
import com.faranjit.meditory.features.detail.presentation.DetailActivity
import com.faranjit.meditory.features.detail.presentation.toDetailModel
import com.faranjit.meditory.features.home.presentation.adapter.meditation.MeditationsAdapter
import com.faranjit.meditory.features.home.presentation.adapter.story.StoriesAdapter
import com.faranjit.meditory.features.home.presentation.adapter.story.StoryItemDecoration
import com.faranjit.meditory.features.home.presentation.model.MeditationModel
import com.faranjit.meditory.features.home.presentation.model.StoryModel
import com.faranjit.meditory.observeLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    private val binding by viewBinding(ActivityHomeBinding::inflate)

    private val onMeditationItemClickListener =
        object : BaseRecyclerAdapter.OnItemClickListener<MeditationModel> {

            override fun onItemClick(item: MeditationModel) {
                startActivity(
                    DetailActivity.newIntent(
                        this@HomeActivity,
                        item.toDetailModel()
                    )
                )
            }
        }

    private val onStoryItemClickListener =
        object : BaseRecyclerAdapter.OnItemClickListener<StoryModel> {

            override fun onItemClick(item: StoryModel) {
                startActivity(
                    DetailActivity.newIntent(
                        this@HomeActivity,
                        item.toDetailModel()
                    )
                )
            }
        }

    private val meditationsAdapter = MeditationsAdapter(onMeditationItemClickListener)

    private val storiesAdapter = StoriesAdapter(onStoryItemClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        observe()
        viewModel.getHomeData()
        viewModel.formatUsername(getString(R.string.banner_text))
    }

    override fun provideViewModel() = viewModel<HomeViewModel>().value

    override fun provideBinding() = binding

    override fun bindViewModel(binding: ActivityHomeBinding) {
        binding.viewModel = viewModel
    }

    private fun initUI() {
        binding.run {
            recyclerMeditations.adapter = meditationsAdapter

            (recyclerStories.layoutManager as GridLayoutManager).spanCount = 2
            recyclerStories.addItemDecoration(
                StoryItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.margin_normal),
                    resources.getDimensionPixelSize(R.dimen.margin_small),
                    resources.getDimensionPixelSize(R.dimen.margin_medium)
                )
            )
            recyclerStories.adapter = storiesAdapter
        }
    }

    private fun observe() {
        viewModel.run {
            observeLiveData(meditationsLiveData) {
                meditationsAdapter.submitList(it)
            }

            observeLiveData(storiesLiveData) {
                storiesAdapter.submitList(it)
            }

            observeLiveData(errorLiveData) {
                showDialog(
                    DialogModel(
                        getString(R.string.error), it,
                        DialogButton(getString(R.string.ok)) {
                            finish()
                        }
                    )
                )
            }
        }
    }
}