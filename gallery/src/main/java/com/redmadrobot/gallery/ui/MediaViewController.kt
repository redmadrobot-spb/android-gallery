package com.redmadrobot.gallery.ui

import androidx.viewpager.widget.ViewPager
import com.redmadrobot.gallery.entity.Media

internal class MediaViewController(
        private val viewPager: ViewPager,
        private val onCurrentItemChangeListener: (Int) -> Unit,
        private val onPlayerControllerVisibilityListener: (Boolean) -> Unit,
        private val onImageZoomListener: (isZoomed: Boolean) -> Unit
) {

    private lateinit var adapter: MediaPagerAdapter

    fun bind(listOfMedia: List<Media>) {

        adapter = MediaPagerAdapter(
                listOfMedia,
                viewPager.context,
                onPlayerControllerVisibilityListener,
                onImageZoomListener
        )
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                when (state) {
                    ViewPager.SCROLL_STATE_DRAGGING,
                    ViewPager.SCROLL_STATE_SETTLING -> adapter.pauseVideoAndHideController()
                    ViewPager.SCROLL_STATE_IDLE -> adapter.resumeVideo(viewPager.currentItem)
                }
            }

            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                // Do not use this callback to start video. There are no views instantiated on first ViewPager layout.
                // Thus adapter has not player to start. Moreover if initial position is 0 this callback is not called.
                onCurrentItemChangeListener(position)
            }
        })
    }

    fun setCurrentItemIndex(currentItemIndex: Int) {
        viewPager.currentItem = currentItemIndex
    }

    fun release() = adapter.clear()
}