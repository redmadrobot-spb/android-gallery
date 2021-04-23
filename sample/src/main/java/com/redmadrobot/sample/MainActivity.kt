package com.redmadrobot.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redmadrobot.gallery.entity.Media
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
            Media.Video(
                    "https://images.pexels.com/photos/459225/pexels-photo-459225.jpeg",
                    "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
            ),
            Media.Video(
                    "https://univerlist.com/media/cache/c2/73/c273d2bb1f9690aad6db6538549ad34e.webp",
                    "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
            ),
            Media.Image(
                    "https://images.pexels.com/photos/459225/pexels-photo-459225.jpeg"
            ),
            Media.Image(
                    "https://images.pexels.com/photos/459225/pexels-photo-459225.jpeg"
            ),
            Media.Video(
                    "https://univerlist.com/media/cache/c2/73/c273d2bb1f9690aad6db6538549ad34e.webp",
                    "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
            ),
            Media.Image(
                    "https://images.pexels.com/photos/459225/pexels-photo-459225.jpeg"
            )
    ))
}
