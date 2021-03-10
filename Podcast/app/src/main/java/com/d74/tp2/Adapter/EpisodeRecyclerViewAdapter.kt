package com.d74.tp2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.d74.tp2.Models.Episode
import com.d74.tp2.R
import com.d74.tp2.UI.Episodes.EpisodesViewModel

class EpisodeRecyclerViewAdapter(private val dataEpisode: MutableList<Episode>,
                                 public val episodeUnit: (Episode) -> Unit) :

    RecyclerView.Adapter<EpisodeRecyclerViewAdapter.EpisodeViewHolder>() {

    class EpisodeViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_episode_item, parent, false) as View
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        this.dataEpisode[position]

        holder.view.findViewById<TextView>(R.id.txtTitreEpisode).text =
            this.dataEpisode[position].title

        holder.view.findViewById<TextView>(R.id.txtDescriptioonEpisode).text =
            this.dataEpisode[position].description

    }
    override fun getItemCount() = this.dataEpisode.size
}