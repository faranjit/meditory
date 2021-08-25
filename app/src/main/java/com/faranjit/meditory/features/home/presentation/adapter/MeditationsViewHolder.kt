package com.faranjit.meditory.features.home.presentation.adapter

import com.faranjit.meditory.base.BaseViewHolder
import com.faranjit.meditory.databinding.ListItemMeditationBinding
import com.faranjit.meditory.features.home.presentation.model.MeditationModel

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class MeditationsViewHolder(
    private val binding: ListItemMeditationBinding
) : BaseViewHolder<MeditationModel>(binding) {

    override fun bind(item: MeditationModel) {
        binding.item = item
    }
}