package com.faranjit.meditory.features.home.presentation.model

import com.faranjit.meditory.base.BaseListItem

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
data class StoryModel(
    val name: String,
    val category: String,
    val image: ImageModel,
    val date: String,
    val text: String
) : BaseListItem<StoryModel>() {

    override fun areItemsTheSame(oldItem: StoryModel, newItem: StoryModel) = oldItem == newItem

    override fun areContentsTheSame(oldItem: StoryModel, newItem: StoryModel) = oldItem == newItem
}