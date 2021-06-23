package com.redmadrobot.gallery.entity

import java.io.Serializable

sealed class  Media  :Serializable{

    data class Image(
            val url: String
    ):Media()

    data class Video(
              val thumbnailUrl: String? = null ,
              val url: String
    ) : Media()

}