package com.redmadrobot.gallery.entity

import java.io.Serializable

data class Media(
        val thumbnailUrl: String,
        val type: MediaType,
        val url: String
) : Serializable