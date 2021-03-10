package com.d74.tp2.UI.Subscriptions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.d74.tp2.Database.PodcastDataBase
import com.d74.tp2.Models.Podcast
import com.d74.tp2.Repositories.SubscriptionRepository

class SubscriptionsViewModel(application: Application) : AndroidViewModel(application) {
    public var allPodcast: LiveData<List<Podcast>>
    val db = PodcastDataBase.getDatabase(application)
    val repositorySubs = SubscriptionRepository(db.PodcastDao())
    val dataSubs = repositorySubs.All()

    // récupère tous les Subscription
    init {
        allPodcast = dataSubs
    }
}

