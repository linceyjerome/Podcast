package com.d74.tp2.Repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.d74.tp2.Models.SearchResult
import com.d74.tp2.Models.SearchResultItem
import com.google.gson.Gson

class PodcastRepository (private val application: Application){

    fun getDataOfSearchingResult(resultList: MutableLiveData<Array<SearchResultItem>>, termeRechercher: String){
        val queue = Volley.newRequestQueue(application)
        val maxResult = 15
        val request = StringRequest(
            Request.Method.GET,
            "https://itunes.apple.com/search?media=podcast&entity=podcast&limit=${maxResult}&term=${termeRechercher}",
            {
                println(it)
                val jsonObject = Gson().fromJson(it, SearchResult::class.java)
                val list = jsonObject.searchResultItems.toTypedArray()
                resultList.value = list
            },
            {
                println("ERREUR")
            })
        queue.add(request)
    }
}