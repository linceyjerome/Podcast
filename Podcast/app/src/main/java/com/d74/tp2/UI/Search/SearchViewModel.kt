package com.d74.tp2.UI.Search

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.d74.tp2.Database.PodcastDataBase
import com.d74.tp2.Models.Episode
import com.d74.tp2.Models.Podcast
import com.d74.tp2.Models.SearchResultItem
import com.d74.tp2.Repositories.PodcastRepository
import com.prof.rssparser.Parser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.nio.charset.Charset

public class SearchViewModel(application: Application) : AndroidViewModel(application) {
    val searchingList: MutableLiveData<Array<SearchResultItem>> = MutableLiveData()
    val repository : PodcastRepository = PodcastRepository(application)
    val search: String = ""


    init {
        repository.getDataOfSearchingResult(searchingList,search)
    }

    //Methode que sert a mettre a jour la liste de recherche
    fun updateListe(searchBar : String){
        repository.getDataOfSearchingResult(searchingList,searchBar)
    }

    //Methode que sert à ajouter un podcast dans la liste Subscription
    fun addSubscription(podcast: SearchResultItem){
        this.viewModelScope.launch(Dispatchers.IO){
            val bitmap = downloadImageFromUrl(URL(podcast.artworkUrl))

            if (bitmap != null) {

                val fileName = "${podcast.collectionId}.jpg"
                val context = getApplication<Application>()
                val photo =  context.getFileStreamPath(fileName).absolutePath
                val parser = Parser.Builder()
                    .context(context)
                    .charset(Charset.forName("UTF-8"))
                    .cacheExpirationMillis(24L * 60L * 60L * 100L)
                    .build()
                val channel = parser.getChannel(podcast.feedUrl)

                try {
                    context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                        it.write(bitmap)
                    }

                    PodcastDataBase.getDatabase(getApplication()).PodcastDao().Add(
                        Podcast(
                            podcastId = podcast.collectionId,
                            nom = podcast.collectionName,
                            auteur = podcast.artistName,
                                photo,
                            feedUrl = podcast.feedUrl
                        )
                    )

                    for (a in channel.articles) {
                        PodcastDataBase.getDatabase(getApplication()).EpisodeDao().Add(
                            Episode(
                                guid = a.guid.toString(),
                                title = a.title.toString(),
                                description = a.description.toString(),
                                audio = a.audio.toString(),
                                FKPodcastId = podcast.collectionId,
                                    episodeId = 0
                            )
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun downloadImageFromUrl(toDownload: URL): ByteArray? {
        val outputStream = ByteArrayOutputStream()
        try {
            val chunk = ByteArray(4096)
            var bytesRead: Int
            val stream: InputStream = toDownload.openStream()
            while (stream.read(chunk).also { bytesRead = it } > 0) {
                outputStream.write(chunk, 0, bytesRead)
            }
        }
        catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return outputStream.toByteArray()
    }
}

