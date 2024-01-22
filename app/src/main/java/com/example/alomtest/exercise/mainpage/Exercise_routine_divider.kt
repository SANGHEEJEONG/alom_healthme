package com.example.alomtest.exercise.mainpage

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class exercise_routine_divider(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        // 마지막 아이템에는 아래 여백을 추가하지 않음
        if (position < itemCount - 1) {
            outRect.bottom = spacing
        }
    }
}