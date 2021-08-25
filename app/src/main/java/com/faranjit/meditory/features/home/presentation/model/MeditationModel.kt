package com.faranjit.meditory.features.home.presentation.model

import com.faranjit.meditory.base.BaseListItem

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
data class MeditationModel(
    val title: String,
    val subtitle: String,
    val image: ImageModel,
    val releaseDate: String,
    val content: String
) : BaseListItem<MeditationModel>() {

    override fun areItemsTheSame(oldItem: MeditationModel, newItem: MeditationModel) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MeditationModel, newItem: MeditationModel) =
        oldItem == newItem
}