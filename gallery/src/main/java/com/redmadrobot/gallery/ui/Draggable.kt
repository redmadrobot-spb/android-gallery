package com.redmadrobot.gallery.ui

interface Draggable {

    var isDraggingEnabled: Boolean

    fun setOnDragListener(listener: (offset: Float) -> Unit)
    fun setOnReleaseDragListener(listener: (offset: Float) -> Unit)
}