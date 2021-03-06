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
    @get:JvmName("watermarkTextDegrees") val watermarkTextDegrees: Float,
    @get:JvmName("watermarkText") vararg val watermarkText: String
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

    class Builder(internal vararg val watermarkText: String) {
        internal var watermarkTextColor: Int = Color.GRAY
        internal var watermarkTextSize: Int = sp2px(12F)
        internal var watermarkTextAlpha: Float = 0.5F
        internal var watermarkTextDegrees: Float = -30F

        fun watermarkTextColor(watermarkTextColor: Int) = apply {
            this.watermarkTextColor = watermarkTextColor
        }

        fun watermarkTextSize(watermarkTextSize: Float) = apply {
            this.watermarkTextSize = sp2px(watermarkTextSize)
        }

        fun watermarkTextAlpha(watermarkTextAlpha: Float) = apply {
            this.watermarkTextAlpha = watermarkTextAlpha
        }

        fun watermarkTextDegrees(watermarkTextDegrees: Float) = apply {
            this.watermarkTextDegrees = watermarkTextDegrees
        }

        fun build(): TextWatermarkDrawable {
            return TextWatermarkDrawable(this)
        }
    }
}