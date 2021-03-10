package com.d74.tp2.UI.Episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d74.tp2.Adapter.EpisodeRecyclerViewAdapter
import com.d74.tp2.Models.Episode
import com.d74.tp2.Models.Podcast
import com.d74.tp2.R

class EpisodesFragment : Fragment() {

    private lateinit var episodeViewModel: EpisodesViewModel
    private val datasetEpisode: MutableList<Episode> = mutableListOf()
    private val datasetPodcast: MutableList<Podcast> = mutableListOf()
    private lateinit var rvEpisode: RecyclerView
    private val episodeRecyclerViewAdapter = EpisodeRecyclerViewAdapter(datasetEpisode){
        this.episodeViewModel.addEpisode(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.rvEpisode = view.findViewById(R.id.rvEpisode)

        this.rvEpisode.adapter = episodeRecyclerViewAdapter

        this.rvEpisode.layoutManager = LinearLayoutManager(this.context)

        // Le factory prend en parametre le Id de l'episode et l'application
        val episodeId = this.requireArguments().getInt("id")
        val episodeFactory = EpisodeViewModelFactory(this.requireActivity().application,episodeId)
        this.episodeViewModel = ViewModelProvider(this,episodeFactory).get(EpisodesViewModel::class.java)

        episodeViewModel.episodeList.observe(viewLifecycleOwner,{
            this.datasetEpisode.clear()
            this.datasetEpisode.addAll(it)
            this.episodeRecyclerViewAdapter.notifyDataSetChanged()
        })
    }
}