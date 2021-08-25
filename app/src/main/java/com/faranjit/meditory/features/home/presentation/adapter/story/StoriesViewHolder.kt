package com.faranjit.meditory.features.home.presentation.adapter.story

import com.faranjit.meditory.base.BaseViewHolder
import com.faranjit.meditory.databinding.ListItemStoryBinding
import com.faranjit.meditory.features.home.presentation.model.StoryModel

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class StoriesViewHolder(
    private val binding: ListItemStoryBinding
) : BaseViewHolder<StoryModel>(binding) {

    override fun bind(item: StoryModel) {
        binding.item = item
    }
}