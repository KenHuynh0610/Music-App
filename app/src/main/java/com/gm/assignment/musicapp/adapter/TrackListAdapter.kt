package com.gm.assignment.musicapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gm.assignment.musicapp.Track
import com.gm.assignment.musicapp.databinding.ArtistInfoRowLayoutBinding


class TrackListAdapter(var context: Activity) :
    RecyclerView.Adapter<TrackListAdapter.ViewHolder>() {

    var trackList: ArrayList<Track> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            ArtistInfoRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(trackList[position]) {
                binding.artistNameTextView.text = "Artist: $artistName"
                binding.genreTextView.text = "Primary Genre: $primaryGenreName"
                binding.trackPriceTextView.text = "Price: $$collectionPrice"
                binding.trackNameTextView.text = "Collection Name: $collectionName"
                binding.releaseDateTextView.text = "Released Date: $releaseDate"
            }
        }
    }

    override fun getItemCount(): Int {
        return if (trackList == null) 0 else trackList.size
    }


    inner class ViewHolder(val binding: ArtistInfoRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}