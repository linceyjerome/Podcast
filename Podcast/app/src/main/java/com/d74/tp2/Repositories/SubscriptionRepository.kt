package com.d74.tp2.Repositories

import androidx.lifecycle.LiveData
import com.d74.tp2.Dao.PodcastDao
import com.d74.tp2.Models.Podcast

//Repository pour les Subscription

class SubscriptionRepository(private val podcastDao: PodcastDao){
    fun All() : LiveData<List<Podcast>> {
       return this.podcastDao.All()
    }

    fun Update(podcast: Podcast) {
        this.podcastDao.Update(podcast)
    }

    fun Remove(podcastId: Int) {
        this.podcastDao.Remove(podcastId)
    }

    fun Single(podcastId: Int) : Podcast {
       return  this.podcastDao.Single(podcastId)
    }
}