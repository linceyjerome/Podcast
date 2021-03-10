package com.d74.tp2.Repositories

import androidx.lifecycle.LiveData
import com.d74.tp2.Dao.EpisodeDao
import com.d74.tp2.Models.Episode

class EpisodeRepository (private val episodeDao : EpisodeDao){

    fun putEpisode(episodeId : Int): LiveData<List<Episode>> {
       return this.episodeDao.getEpisodes(episodeId)
    }

    fun Single(episodeId : Int): Episode {
        return this.episodeDao.Single(episodeId)
    }
}