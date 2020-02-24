package com.hurryyu.rvwatermark.drawable

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.hurryyu.rvwatermark.sp2px

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: 1037914505@qq.com
 * Time: 2020/2/22
 * Version: 1.0
 * Description:
 * ===================================================================
 */
class TextWatermarkDrawable private constructor(
    watermarkTextColor: Int,
    watermarkTextSize: Int,
    watermarkTextAlpha: Float,
    private val watermarkTextDegrees: Float,
    private vararg val watermarkText: String
) : BaseWatermarkDrawable() {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    companion object {
        inline fun build(
            vararg watermarkText: String,
            block: Builder.() -> Unit = {}
        ): TextWatermarkDrawable {
            return Builder(*watermarkText).apply(block).build()
        }
    }

    init {
        paint.color = watermarkTextColor
        paint.textSize = watermarkTextSize.toFloat()
        paint.alpha = (255 * watermarkTextAlpha).toInt()
        paint.textAlign = Paint.Align.CENTER
    }

    constructor(builder: Builder) : this(
        builder.watermarkTextColor,
        builder.watermarkTextSize,
        builder.watermarkTextAlpha,
        builder.watermarkTextDegrees,
        *builder.watermarkText
    )

    override fun draw(canvas: Canvas) {
        if (watermarkText.isEmpty()) {
            return
        }
        val rect = bounds

        canvas.save()
        canvas.rotate(
            watermarkTextDegrees,
            rect.centerX().toFloat(),
            rect.centerY().toFloat()
        )
        var text = watermarkText[0]
        if (watermarkText.size > 1) {
            text = watermarkText[currentDrawIndex % watermarkText.size]
        }
        val fontMetrics = paint.fontMetrics
        val offset = (fontMetrics.bottom + fontMetrics.top) / 2
        canvas.drawText(
            text,
            rect.centerX().toFloat(),
            rect.bottom - (rect.bottom - rect.top) / 2F - offset,
            paint
        )
        canvas.restore()
    }

    class Builder(vararg val watermarkText: String) {
        @JvmField
        var watermarkTextColor: Int = Color.GRAY
        @JvmField
        var watermarkTextSize: Int = sp2px(12F)
        @JvmField
        var watermarkTextAlpha: Float = 0.5F
        @JvmField
        var watermarkTextDegrees: Float = -30F

        fun setWatermarkTextColor(watermarkTextColor: Int): Builder {
            this.watermarkTextColor = watermarkTextColor
            return this
        }

        fun setWatermarkTextSize(watermarkTextSize: Float): Builder {
            this.watermarkTextSize = sp2px(watermarkTextSize)
            return this
        }

        fun setWatermarkTextAlpha(watermarkTextAlpha: Float): Builder {
            this.watermarkTextAlpha = watermarkTextAlpha
            return this
        }

        fun setWatermarkTextDegrees(watermarkTextDegrees: Float): Builder {
            this.watermarkTextDegrees = watermarkTextDegrees
            return this
        }

        fun build(): TextWatermarkDrawable {
            return TextWatermarkDrawable(this)
        }
    }
}