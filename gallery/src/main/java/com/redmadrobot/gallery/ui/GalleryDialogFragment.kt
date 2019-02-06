package com.redmadrobot.gallery.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.annotation.DimenRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager
import com.redmadrobot.gallery.R
import com.redmadrobot.gallery.entity.Media
import com.redmadrobot.gallery.util.argument
import kotlin.math.min

open class GalleryDialogFragment : DialogFragment() {

    private val listOfMedia: ArrayList<Media> by argument(ARG_LIST_OF_MEDIA)
    private val position: Int by argument(ARG_INITIALLY_CHECKED_ITEM_INDEX)
    private val isRotationEnabled: Boolean by argument(ARG_FORCE_ROTATION)
    private val layoutRes: Int by argument(ARG_LAYOUT_RES)
    private val dragLayoutId: Int by argument(ARG_DRAG_LAYOUT_ID)
    private val viewPagerId: Int by argument(ARG_VIEW_PAGER_ID)
    private val swipeToDismissLengthDimen: Int by argument(ARG_SWIPE_TO_DISMISS_LENGTH_DIMEN)

    private var lastPosition: Int = 0
    private var initialOrientation: Int = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    private val dismissPathLength by lazy { resources.getDimensionPixelSize(swipeToDismissLengthDimen) }
    private lateinit var mediaViewController: MediaViewController
    protected lateinit var rootView: View
    protected lateinit var draggableView: Draggable
    protected lateinit var viewPager: ViewPager

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen)
    }

    final override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutRes, container, false)
        rootView = view

        val dragLayoutView = rootView.findViewById<View>(dragLayoutId)
                ?: throw IllegalArgumentException("Can not find `Draggable` layout corresponding to the passed id")

        draggableView = dragLayoutView as? Draggable
                ?: throw IllegalArgumentException("View corresponding to the dragLayoutId must implement DragLayout interface")

        viewPager = rootView.findViewById(viewPagerId)
                ?: throw IllegalArgumentException("Can not find view pager corresponding to the passed id")

        return view
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        draggableView.setOnDragListener { offset ->
            rootView.alpha = 1 - min(Math.abs(offset / (3 * dismissPathLength)), 1f)
            viewPager.translationY = -offset
        }
        draggableView.setOnReleaseDragListener { offset ->
            if (Math.abs(offset) > dismissPathLength) {
                viewPager.isVisible = false
                dismiss()
            } else {
                rootView.alpha = 1f
                viewPager.translationY = 0f
            }
        }

        lastPosition = savedInstanceState?.getInt(STATE_LAST_CHECKED_ITEM_INDEX) ?: position

        mediaViewController = MediaViewController(
                viewPager = viewPager,
                onCurrentItemChangeListener = { index ->
                    lastPosition = index
                },
                onPlayerControllerVisibilityListener = {},
                onImageZoomListener = { isZoomed -> draggableView.isDraggingEnabled = !isZoomed }
        ).apply {
            bind(listOfMedia)
            setCurrentItemIndex(lastPosition)
        }
    }

    @CallSuper
    override fun onResume() {
        activity?.run {
            initialOrientation = requestedOrientation
            if (isRotationEnabled) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
            }
        }
        applyWindowFullscreenStyle()
        super.onResume()
    }

    @CallSuper
    override fun onPause() {
        activity?.requestedOrientation = initialOrientation
        super.onPause()
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_LAST_CHECKED_ITEM_INDEX, lastPosition)
        super.onSaveInstanceState(outState)
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        mediaViewController.release()
    }

    private fun applyWindowFullscreenStyle() {
        dialog?.window?.apply {
            attributes = attributes?.apply {
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
            }
        }
    }

    companion object {
        private const val STATE_LAST_CHECKED_ITEM_INDEX = "state_last_checked_item_index"
        private const val ARG_LIST_OF_MEDIA = "arg_list_of_media"
        private const val ARG_INITIALLY_CHECKED_ITEM_INDEX = "arg_initially_checked_item_index"
        private const val ARG_FORCE_ROTATION = "arg_force_rotation"
        private const val ARG_LAYOUT_RES = "arg_layout_res"
        private const val ARG_DRAG_LAYOUT_ID = "arg_drag_layout_id"
        private const val ARG_VIEW_PAGER_ID = "arg_view_pager_id"
        private const val ARG_SWIPE_TO_DISMISS_LENGTH_DIMEN = "arg_swipe_to_dismiss_length_dimen"

        @JvmOverloads
        @JvmStatic
        fun <T : GalleryDialogFragment> T.applyGalleryParams(
                media: ArrayList<Media>,
                position: Int,
                isRotationEnabled: Boolean = false,
                @DimenRes swipeToDismissLengthDimen: Int = R.dimen.default_dismiss_length,
                @LayoutRes layoutRes: Int = R.layout.fragment_gallery,
                @IdRes draggableLayoutId: Int = R.id.ag_dragLayout,
                @IdRes viewPagerId: Int = R.id.ag_viewPager
        ): T {
            if (arguments == null) {
                arguments = Bundle()
            }
            arguments?.apply {
                putSerializable(ARG_LIST_OF_MEDIA, media)
                putInt(ARG_INITIALLY_CHECKED_ITEM_INDEX, position)
                putInt(ARG_LAYOUT_RES, layoutRes)
                putInt(ARG_DRAG_LAYOUT_ID, draggableLayoutId)
                putInt(ARG_VIEW_PAGER_ID, viewPagerId)
                putInt(ARG_SWIPE_TO_DISMISS_LENGTH_DIMEN, swipeToDismissLengthDimen)
                putBoolean(ARG_FORCE_ROTATION, isRotationEnabled)
            }
            return this
        }
    }
}
