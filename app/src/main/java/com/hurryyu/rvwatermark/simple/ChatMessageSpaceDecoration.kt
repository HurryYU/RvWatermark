package com.hurryyu.rvwatermark.simple

import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: 1037914505@qq.com
 * Time: 2020/2/21
 * Version: 1.0
 * Description:
 * ===================================================================
 */
class ChatMessageSpaceDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val childAdapterPosition = parent.getChildAdapterPosition(view)
        if (childAdapterPosition == 0) {
            outRect.top = dp2px(12f).toInt()
            outRect.bottom = dp2px(12f).toInt()
        } else {
            outRect.bottom = dp2px(12f).toInt()
        }
    }

    private fun dp2px(dpValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpValue,
            Resources.getSystem().displayMetrics
        )
    }
}