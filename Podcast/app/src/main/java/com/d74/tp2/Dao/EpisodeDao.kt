package com.d74.tp2.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.d74.tp2.Models.Episode

@Dao
interface EpisodeDao {
    @Query("SELECT * from Episode")
    fun All() : LiveData<List<Episode>>

    @Query("SELECT * from Episode WHERE guid = :episodeId")
    fun Find(episodeId: Int): LiveData<List<Episode>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun Add(e: Episode) : Long

    @Update
    fun Update(e: Episode)

    @Query("DELETE FROM Episode WHERE episodeId = :episodeId")
    fun Remove(episodeId: Int)

    @Query("SELECT * FROM Episode WHERE episodeId = :episodeId")
    fun Single(episodeId: Int): Episode

    //récupère tous les épisodes d'un Podcast selectionné
    @Query("Select * from Episode where episode.FKPodcastId = :podcastId order by episode.title asc")
    fun getEpisodes(podcastId: Int) : LiveData<List<Episode>>

}