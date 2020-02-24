package com.hurryyu.rvwatermark

import android.content.res.Resources
import android.util.TypedValue

fun dp2px(dpValue: Float): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dpValue,
    Resources.getSystem().displayMetrics
).toInt()

fun sp2px(spValue: Float): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    spValue,
    Resources.getSystem().displayMetrics
).toInt()