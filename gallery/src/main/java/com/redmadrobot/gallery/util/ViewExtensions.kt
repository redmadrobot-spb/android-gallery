package com.redmadrobot.gallery.util

import android.support.annotation.Dimension
import android.support.annotation.Dimension.DP
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View

internal fun View.dpToPx(@Dimension(unit = DP) dp: Float): Float =
    dpToPx(resources.displayMetrics, dp)

private fun dpToPx(displayMetrics: DisplayMetrics, @Dimension(unit = DP) dp: Float) =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        displayMetrics
    )
