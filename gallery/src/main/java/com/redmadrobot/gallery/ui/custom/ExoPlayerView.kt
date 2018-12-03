package com.redmadrobot.gallery.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import com.google.android.exoplayer2.ui.PlayerView

/**
 * Subclass of [PlayerView] which overrides controller show/hide touch events.
 */
internal class ExoPlayerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : PlayerView(context, attrs, defStyleAttr) {

    private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent?): Boolean = true

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            if (isControllerVisible) {
                hideController()
            } else {
                showController()
            }
            return true
        }
    })

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean = gestureDetector.onTouchEvent(ev)

    override fun onTrackballEvent(ev: MotionEvent?): Boolean = gestureDetector.onTouchEvent(ev)
}