package com.faranjit.meditory.features.home.presentation.adapter.story

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class StoryItemDecoration(
    private val margin: Int,
    private val horizontal: Int,
    private val vertical: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = vertical
        outRect.bottom = vertical
        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.left = margin
            outRect.right = horizontal
        } else {
            outRect.left = horizontal
            outRect.right = margin
        }
    }
}