package com.faranjit.meditory.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
abstract class BaseViewHolder<T>(binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: T)
}