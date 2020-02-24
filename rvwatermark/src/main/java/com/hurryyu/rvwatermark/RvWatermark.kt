package com.hurryyu.rvwatermark

import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView
import com.hurryyu.rvwatermark.drawable.BaseWatermarkDrawable
import kotlin.math.abs
import kotlin.math.ceil

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: 1037914505@qq.com
 * Time: 2020/2/22
 * Version: 1.0
 * Description:
 * ===================================================================
 */
class RvWatermark private constructor(
    private val drawable: BaseWatermarkDrawable,
    private val watermarkColumnNumber: Int,
    private val watermarkRowHeight: Int,
    private val isFollowScroll: Boolean
) : RecyclerView.ItemDecoration() {

    private var scrollY = 0

    constructor(builder: Builder) : this(
        builder.drawable,
        builder.watermarkColumnNumber,
        builder.watermarkRowHeight,
        builder.isFollowScroll
    )

    companion object {
        inline fun build(
            drawable: BaseWatermarkDrawable,
            block: Builder.() -> Unit = {}
        ): RvWatermark {
            return Builder(drawable).apply(block).build()
        }
    }

    fun bindToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (isFollowScroll) {
                    scrollY += dy
                }
            }
        })
        recyclerView.addItemDecoration(this)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val scrollUp = -scrollY
        val rectWidth = parent.width / watermarkColumnNumber
        val startLine = abs(scrollY / watermarkRowHeight)
        // 向上取整
        val totalLine = ceil((parent.height + scrollY) / watermarkRowHeight.toFloat()).toInt()
        for (row in startLine until totalLine) {
            val rectTop = scrollUp + watermarkRowHeight * row
            var rectLeft = 0
            for (column in 0 until watermarkColumnNumber) {
                drawable.setBounds(
                    rectLeft,
                    rectTop,
                    rectLeft + rectWidth,
                    rectTop + watermarkRowHeight
                )

                drawable.currentDrawIndex = row * watermarkColumnNumber + column
                drawable.draw(c)
                rectLeft += rectWidth
            }
        }
    }

    class Builder(val drawable: BaseWatermarkDrawable) {
        // 屏蔽自动生成的get&set,方便java
        @JvmField
        var watermarkColumnNumber = 3
        @JvmField
        var watermarkRowHeight = dp2px(120F)
        @JvmField
        var isFollowScroll = true

        fun setWatermarkColumnNumber(watermarkColumnNumber: Int): Builder {
            this.watermarkColumnNumber = watermarkColumnNumber
            return this
        }

        fun setWatermarkRowHeight(watermarkRowHeight: Int): Builder {
            this.watermarkRowHeight = dp2px(watermarkRowHeight.toFloat())
            return this
        }

        fun setIsFollowScroll(isFollowScroll: Boolean): Builder {
            this.isFollowScroll = isFollowScroll
            return this
        }

        fun build() = RvWatermark(this)
    }
}