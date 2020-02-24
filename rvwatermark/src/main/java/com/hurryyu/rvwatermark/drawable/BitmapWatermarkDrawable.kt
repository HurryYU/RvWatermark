package com.hurryyu.rvwatermark.drawable

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: 1037914505@qq.com
 * Time: 2020/2/23
 * Version: 1.0
 * Description:
 * ===================================================================
 */
class BitmapWatermarkDrawable(
    private val bitmap: Bitmap,
    private val scaleValue: Float,
    alpha: Float,
    private val degrees: Float
) :
    BaseWatermarkDrawable() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val matrix = Matrix()
    private val bitmapWidth = bitmap.width
    private val bitmapHeight = bitmap.height

    companion object {
        inline fun build(bitmap: Bitmap, block: Builder.() -> Unit = {}): BitmapWatermarkDrawable {
            return Builder(bitmap).apply(block).build()
        }
    }

    constructor(builder: Builder) : this(
        builder.bitmap,
        builder.watermarkBitmapScaleValue,
        builder.watermarkBitmapAlpha,
        builder.watermarkBitmapDegrees
    )

    init {
        paint.alpha = (255 * alpha).toInt()
    }

    override fun draw(canvas: Canvas) {
        val rect = bounds
        canvas.save()
        canvas.translate(rect.centerX().toFloat(), rect.centerY().toFloat())
        canvas.rotate(degrees, rect.centerX().toFloat(), rect.centerY().toFloat())
        matrix.reset()
        matrix.postTranslate(-bitmapWidth / 2F, -bitmapHeight / 2F)
        matrix.postScale(scaleValue, scaleValue)
        canvas.drawBitmap(bitmap, matrix, paint)
        canvas.restore()
    }

    class Builder(val bitmap: Bitmap) {
        @JvmField
        var watermarkBitmapScaleValue: Float = 1F
        @JvmField
        var watermarkBitmapAlpha: Float = 0.5F
        @JvmField
        var watermarkBitmapDegrees: Float = 0F

        fun setWatermarkBitmapScaleValue(watermarkBitmapScaleValue: Float): Builder {
            this.watermarkBitmapScaleValue = watermarkBitmapScaleValue
            return this
        }

        fun setWatermarkBitmapAlpha(watermarkBitmapAlpha: Float): Builder {
            this.watermarkBitmapAlpha = watermarkBitmapAlpha
            return this
        }

        fun setWatermarkBitmapDegrees(watermarkBitmapDegrees: Float): Builder {
            this.watermarkBitmapDegrees = watermarkBitmapDegrees
            return this
        }

        fun build(): BitmapWatermarkDrawable {
            return BitmapWatermarkDrawable(this)
        }
    }
}