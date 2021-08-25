package com.faranjit.meditory.features.home.presentation.model

import com.faranjit.meditory.base.BaseListItem
import com.faranjit.meditory.features.home.data.response.Image

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
data class StoryModel(
    val name: String,
    val category: String,
    val image: Image,
    val date: String,
    val text: String
) : BaseListItem<StoryModel>() {

    override fun areItemsTheSame(oldItem: StoryModel, newItem: StoryModel) = oldItem == newItem

    override fun areContentsTheSame(oldItem: StoryModel, newItem: StoryModel) = oldItem == newItem
}