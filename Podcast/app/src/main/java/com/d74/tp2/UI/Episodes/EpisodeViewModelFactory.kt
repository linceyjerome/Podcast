package com.d74.tp2.UI.Episodes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EpisodeViewModelFactory(val application: Application,private val episodeId : Int):
ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpisodesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EpisodesViewModel(this.application, this.episodeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}