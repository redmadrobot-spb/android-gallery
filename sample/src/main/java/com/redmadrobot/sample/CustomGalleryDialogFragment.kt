package com.redmadrobot.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.redmadrobot.gallery.entity.Media
import com.redmadrobot.gallery.ui.GalleryDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_custom_gallery.*

class CustomGalleryDialogFragment : GalleryDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appCompatActivity = activity as? AppCompatActivity
        appCompatActivity?.setSupportActionBar(toolbar)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { dismiss() }
    }

    companion object {
        fun newInstance(media: ArrayList<Media>, position: Int) =
                CustomGalleryDialogFragment()
                        .applyGalleryParams(
                                media = media,
                                position = position,
                                layoutRes = R.layout.dialog_fragment_custom_gallery,
                                viewPagerId = R.id.customIdViewPager,
                                draggableLayoutId = R.id.customIdDragLayout,
                                isRotationEnabled = true,
                                swipeToDismissLengthDimen = R.dimen.default_dismiss_length
                        )
    }
}
