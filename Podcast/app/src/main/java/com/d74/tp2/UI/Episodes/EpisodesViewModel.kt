package com.d74.tp2.UI.Episodes

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.d74.tp2.Database.PodcastDataBase
import com.d74.tp2.Models.Episode
import com.d74.tp2.Repositories.EpisodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL

class EpisodesViewModel(application: Application, episodeId: Int) : AndroidViewModel(application) {
    val episodeList: LiveData<List<Episode>>
    val db = PodcastDataBase.getDatabase(application)
    val repositorySubs = EpisodeRepository(db.EpisodeDao())
    private val dataEpisode = repositorySubs.putEpisode(episodeId)

    init {
        episodeList = dataEpisode
    }


    public fun addEpisode(e: Episode){
        this.viewModelScope.launch(Dispatchers.IO){
            val fileName = "${e.episodeId}.mp3"
            val context = getApplication<Application>()
            downloadAndSaveAs(context,URL(e.audio),fileName)
            println("Downloaded !")
        }
    }

    public fun deleteEpisode(e: Episode){
        this.viewModelScope.launch(Dispatchers.IO){
            val fileName = "${e.episodeId}.mp3"
            val context = getApplication<Application>()
            PodcastDataBase.getDatabase(getApplication()).EpisodeDao().Remove(e.episodeId)
            println("Removed !")
        }
    }

     fun downloadAndSaveAs(context: Context, url: URL, filename: String): String? {
        try {
            context.openFileOutput(filename, Context.MODE_PRIVATE).use { outputStream ->
                val chunk = ByteArray(4096)
                var bytesRead: Int
                val inputStream = url.openStream()
                while (inputStream.read(chunk).also { bytesRead = it } > 0) {
                    outputStream.write(chunk, 0, bytesRead)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
             println("error")
        }
        return context.getFileStreamPath(filename).absolutePath
    }
}