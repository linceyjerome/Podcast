package com.d74.tp2.UI.Subscriptions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d74.tp2.Adapter.SubscriptionRecyclerViewAdapter
import com.d74.tp2.Models.Podcast
import com.d74.tp2.R

class SubscriptionsFragment : Fragment() {
    private lateinit var subscriptionsViewModel: SubscriptionsViewModel
    private val datasetSubs: MutableList<Podcast> = mutableListOf()
    private lateinit var rvSubscription:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subscriptions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.rvSubscription = view.findViewById(R.id.rvSubscription)

        rvSubscription.adapter = SubscriptionRecyclerViewAdapter(datasetSubs){
            GetEpisode(it)
        }

        rvSubscription.layoutManager = LinearLayoutManager(this.context)

        this.subscriptionsViewModel = ViewModelProvider(this)
            .get(SubscriptionsViewModel::class.java)

        this.subscriptionsViewModel.allPodcast.observe(viewLifecycleOwner, {
            this.datasetSubs.clear()
            this.datasetSubs.addAll(it)
            rvSubscription.adapter!!.notifyDataSetChanged()
        })
    }

    //j'envoie le id de l'episode dans le fragment episode
    fun GetEpisode(id : Int){
        requireView().findNavController().navigate(R.id.nav_episodes, bundleOf(Pair("id",id)))
        Log.d("id",id.toString())
    }


}

