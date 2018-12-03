package com.redmadrobot.gallery.util

import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.annotation.Dimension
import androidx.annotation.Dimension.DP

internal fun View.dpToPx(@Dimension(unit = DP) dp: Float): Float =
        dpToPx(resources.displayMetrics, dp)

private fun dpToPx(displayMetrics: DisplayMetrics, @Dimension(unit = DP) dp: Float) =
        TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                displayMetrics
        )
