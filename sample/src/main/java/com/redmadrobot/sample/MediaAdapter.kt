package com.redmadrobot.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redmadrobot.gallery.entity.Media
import kotlinx.android.synthetic.main.item_media.view.*

class MediaAdapter : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var items: List<Media> = emptyList()

    var onItemClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                    LayoutInflater
                            .from(parent.context)
                            .inflate(R.layout.item_media, parent, false)
            )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position], position)

    override fun getItemCount(): Int =
            items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(media: Media, position: Int) {

            Glide.with(itemView)
                    .load(media.thumbnailUrl)
                    .into(itemView.previewImageView)

            itemView.setOnClickListener { onItemClick(position) }
        }
    }
}

