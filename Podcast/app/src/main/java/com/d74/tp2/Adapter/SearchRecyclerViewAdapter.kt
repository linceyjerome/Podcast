package com.d74.tp2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.d74.tp2.Models.SearchResultItem
import com.d74.tp2.R
import com.squareup.picasso.Picasso


class SearchRecyclerViewAdapter (
    private val dataSearch : MutableList<SearchResultItem>,

    // variable qui me permet d'ajouter un podcast en passant par référence la variable podcast
    public val subs: (SearchResultItem) -> Unit) :
    RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewViewHolder>(){

    class SearchViewViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_podcast_item, parent, false) as View
        return SearchViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewViewHolder, position: Int) {
        val podcast = this.dataSearch[position]
        val imgPodcast = holder.view.findViewById<ImageView>(R.id.img)
        val btnSubscribe  = holder.view.findViewById<Button>(R.id.btnSubscribe)

        holder.view.findViewById<TextView>(R.id.txtTitre).text =
            this.dataSearch[position].collectionName

        holder.view.findViewById<TextView>(R.id.txtAuteur).text =
            this.dataSearch[position].artistName

        holder.view.findViewById<TextView>(R.id.txtType).text =
            this.dataSearch[position].primaryGenreName

        Picasso.get().load(podcast.artworkThumbnailUrl).into(imgPodcast)

        btnSubscribe.setOnClickListener {
            subs(podcast)

            val toast = Toast.makeText(
                holder.view.context,"${this.dataSearch[position].collectionName} is added to the subscription list",
                Toast.LENGTH_LONG
            )
            toast.show()

            btnSubscribe.isVisible = false
        }
    }
    override fun getItemCount() = this.dataSearch.size
}