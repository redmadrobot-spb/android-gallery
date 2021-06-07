package com.redmadrobot.gallery.entity

import java.io.Serializable

sealed class  Media  :Serializable{

    data class Image(
            val url: String
    ):Media()

    data class Video(
             val thumbnailUrl: String,
              val url: String
    ) : Media()

}