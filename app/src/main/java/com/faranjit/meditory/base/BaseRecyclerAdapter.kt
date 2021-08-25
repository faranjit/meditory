package com.faranjit.meditory.base

import androidx.recyclerview.widget.ListAdapter

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
abstract class BaseRecyclerAdapter<T : BaseListItem<T>, VH : BaseViewHolder<T>>(
    private val clickListener: OnItemClickListener<T>? = null
) : ListAdapter<T, VH>(BaseRecyclerItemCallback<T>()) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.also { item ->
            holder.bind(item)

            holder.itemView.setOnClickListener {
                clickListener?.onItemClick(item)
            }
        }
    }

    interface OnItemClickListener<T : BaseListItem<T>> {

        /**
         * Tiklanan list item'i ui'ya doner.
         */
        fun onItemClick(item: T)
    }
}