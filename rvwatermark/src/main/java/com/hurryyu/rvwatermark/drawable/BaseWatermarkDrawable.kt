package com.hurryyu.rvwatermark.drawable

import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: 1037914505@qq.com
 * Time: 2020/2/22
 * Version: 1.0
 * Description:
 * ===================================================================
 */
abstract class BaseWatermarkDrawable : Drawable() {
    internal var currentDrawIndex = -1

    fun currentDrawIndex() = currentDrawIndex

    override fun setAlpha(alpha: Int) {
    }

    override fun getOpacity(): Int = PixelFormat.UNKNOWN

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

}