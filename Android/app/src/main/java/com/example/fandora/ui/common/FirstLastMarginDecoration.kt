package com.example.fandora.ui.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FirstLastMarginDecoration(
    private val sideMargin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        when (position) {
            0 -> outRect.left = sideMargin
            itemCount - 1 -> outRect.right = sideMargin
        }
    }
}

