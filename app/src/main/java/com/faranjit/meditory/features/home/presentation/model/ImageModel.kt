package com.faranjit.meditory.features.home.presentation.model

import com.faranjit.meditory.base.BaseListItem

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
data class ImageModel(
    val small: String,
    val large: String
) : BaseListItem<ImageModel>() {

    override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel) = oldItem == newItem

    override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel) = oldItem == newItem
}