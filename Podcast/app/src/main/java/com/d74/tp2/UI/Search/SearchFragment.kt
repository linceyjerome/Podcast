package com.d74.tp2.UI.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d74.tp2.Adapter.SearchRecyclerViewAdapter
import com.d74.tp2.Models.SearchResultItem
import com.d74.tp2.R

public class SearchFragment : Fragment() {

    public lateinit var searchViewModel: SearchViewModel
    private lateinit var rvListePodcast:RecyclerView
    private val datasetSearch: MutableList<SearchResultItem> = mutableListOf()
    private val searchRecyclerViewAdapter = SearchRecyclerViewAdapter(this.datasetSearch){
        this.searchViewModel.addSubscription(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.rvListePodcast = view.findViewById(R.id.rvListePodcast)
        this.rvListePodcast.layoutManager = LinearLayoutManager(this.context)
        this.rvListePodcast.adapter = searchRecyclerViewAdapter

        // la bar de recherche
        var search = view.findViewById<EditText>(R.id.BarRecherche).text.toString()

        //bouton recherche
        val btnSearch = view.findViewById<ImageButton>(R.id.btnSearch)

        this.searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        //la methode update met a jour la liste de recherche
        btnSearch.setOnClickListener{
            search = view.findViewById<EditText>(R.id.BarRecherche).text.toString()
            this.searchViewModel.updateListe(search)
        }

        searchViewModel.searchingList.observe(viewLifecycleOwner, {
            datasetSearch.clear()
            datasetSearch.addAll(it)
            this.searchRecyclerViewAdapter.notifyDataSetChanged()
        })



    }
}