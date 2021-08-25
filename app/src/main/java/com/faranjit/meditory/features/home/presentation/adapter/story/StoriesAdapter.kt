package com.faranjit.meditory.features.home.presentation.adapter.story

import android.view.LayoutInflater
import android.view.ViewGroup
import com.faranjit.meditory.base.BaseRecyclerAdapter
import com.faranjit.meditory.databinding.ListItemStoryBinding
import com.faranjit.meditory.features.home.presentation.model.StoryModel

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class StoriesAdapter(
    onItemClickListener: OnItemClickListener<StoryModel>
) : BaseRecyclerAdapter<StoryModel, StoriesViewHolder>(onItemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StoriesViewHolder(
            ListItemStoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
}