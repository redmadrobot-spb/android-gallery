package com.redmadrobot.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redmadrobot.gallery.entity.Media
import com.redmadrobot.gallery.entity.MediaType
import com.redmadrobot.gallery.ui.GalleryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView.apply {
            adapter = MediaAdapter().apply {
                items = listOfMedia
                onItemClick = { position ->
                    GalleryFragment
                            .create(listOfMedia, position)
                            .show(supportFragmentManager, "fragment_tag_gallery")
                }
            }
            setHasFixedSize(true)
        }
    }

    private val listOfMedia = ArrayList(listOf(
            Media(
                    "http://mazwai.com/system/posts/videos/000/000/218/poster/severe-storm-over-mandurah.png",
                    MediaType.VIDEO,
                    "http://mazwai.com/system/posts/videos/000/000/218/original/severe-storm-over-mandurah.mp4"
            ),
            Media(
                    "http://mazwai.com/system/posts/videos/000/000/205/poster/goomalling-storm.png",
                    MediaType.VIDEO,
                    "http://mazwai.com/system/posts/videos/000/000/205/original/goomalling-storm.mp4"
            ),
            Media(
                    "http://mazwai.com/system/posts/videos/000/000/183/poster_3/a_sky_full_of_stars.png",
                    MediaType.VIDEO,
                    "http://mazwai.com/system/posts/videos/000/000/183/original/a_sky_full_of_stars.mp4"
            ),
            Media(
                    "https://images.pexels.com/photos/459225/pexels-photo-459225.jpeg",
                    MediaType.IMAGE,
                    "https://images.pexels.com/photos/459225/pexels-photo-459225.jpeg"
            ),
            Media(
                    "https://images.pexels.com/photos/257840/pexels-photo-257840.jpeg",
                    MediaType.IMAGE,
                    "https://images.pexels.com/photos/257840/pexels-photo-257840.jpeg"
            ),
            Media(
                    "https://images.pexels.com/photos/39811/pexels-photo-39811.jpeg",
                    MediaType.IMAGE,
                    "https://images.pexels.com/photos/39811/pexels-photo-39811.jpeg"
            ),
            Media(
                    "https://images.pexels.com/photos/1039302/pexels-photo-1039302.jpeg",
                    MediaType.IMAGE,
                    "https://images.pexels.com/photos/1039302/pexels-photo-1039302.jpeg"
            ),
            Media(
                    "http://mazwai.com/system/posts/videos/000/000/170/poster/tom_poederbach--las_vegas_blvd_copy_for_vimeo_by_philip_bloom.png",
                    MediaType.VIDEO,
                    "http://mazwai.com/system/posts/videos/000/000/170/original/tom_poederbach--las_vegas_blvd_copy_for_vimeo_by_philip_bloom.mp4"
            ),
            Media(
                    "http://mazwai.com/system/posts/videos/000/000/161/poster/leonard_soosay--missfit.png",
                    MediaType.VIDEO,
                    "http://mazwai.com/system/posts/videos/000/000/161/original/leonard_soosay--missfit.mp4"
            ),
            Media(
                    "https://images.pexels.com/photos/4827/nature-forest-trees-fog.jpeg",
                    MediaType.IMAGE,
                    "https://images.pexels.com/photos/4827/nature-forest-trees-fog.jpeg"
            ),
            Media(
                    "https://images.pexels.com/photos/248771/pexels-photo-248771.jpeg",
                    MediaType.IMAGE,
                    "https://images.pexels.com/photos/248771/pexels-photo-248771.jpeg"
            ),
            Media(
                    "http://mazwai.com/system/posts/videos/000/000/151/poster/gregory_latham--safran.png",
                    MediaType.VIDEO,
                    "http://mazwai.com/system/posts/videos/000/000/151/original/gregory_latham--safran.mp4"
            ),
            Media(
                    "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg",
                    MediaType.IMAGE,
                    "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg"
            )
    ))
}
