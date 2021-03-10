package com.d74.tp2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.d74.tp2.Models.Podcast
import com.d74.tp2.R
import com.squareup.picasso.Picasso
import java.io.File

class SubscriptionRecyclerViewAdapter (private val dataSearch : MutableList<Podcast>,
                                       private val episodes: (Int) -> Unit):
    RecyclerView.Adapter<SubscriptionRecyclerViewAdapter.SubscriptionViewHolder>(){
    class SubscriptionViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_podcast_item, parent, false) as View
        return SubscriptionViewHolder(view)
    }
    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        val podcast = this.dataSearch[position]
        val type =  holder.view.findViewById<TextView>(R.id.txtType)
        val imgPodcast = holder.view.findViewById<ImageView>(R.id.img)
        val btnSubscribe  = holder.view.findViewById<Button>(R.id.btnSubscribe)
        val constraintLayout = holder.view.findViewById<ConstraintLayout>(R.id.podcastItem)

        // chemin pour la récupération
        val fileName = "${podcast.podcastId}.jpg"
        val context = holder.view.context.applicationContext
        val file = File(context.filesDir,fileName)

        //stock l'image sauvgarder dans l'imageView
        Picasso.get().load(file).into(imgPodcast)

        holder.view.findViewById<TextView>(R.id.txtTitre).text = this.dataSearch[position].nom
        holder.view.findViewById<TextView>(R.id.txtAuteur).text = this.dataSearch[position].auteur

        type.isVisible = false
        btnSubscribe.isVisible = false

        constraintLayout.setOnClickListener {
            episodes(podcast.podcastId)

            val toast = Toast.makeText(
                holder.view.context, this.dataSearch[position].nom,
                Toast.LENGTH_LONG
            )
            toast.show()
        }
    }

    override fun getItemCount() = this.dataSearch.size
}