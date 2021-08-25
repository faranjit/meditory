package com.faranjit.meditory.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.faranjit.meditory.base.BaseRecyclerAdapter
import com.faranjit.meditory.databinding.ListItemMeditationBinding
import com.faranjit.meditory.features.home.presentation.model.MeditationModel

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class MeditationsAdapter(
    onItemClickListener: OnItemClickListener<MeditationModel>
) : BaseRecyclerAdapter<MeditationModel, MeditationsViewHolder>(onItemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MeditationsViewHolder(
            ListItemMeditationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
}