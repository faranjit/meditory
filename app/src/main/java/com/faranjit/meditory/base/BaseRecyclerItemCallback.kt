package com.faranjit.meditory.base

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class BaseRecyclerItemCallback<T : BaseListItem<T>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem.areContentsTheSame(oldItem, newItem)
}

abstract class BaseListItem<T> {
    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean
}