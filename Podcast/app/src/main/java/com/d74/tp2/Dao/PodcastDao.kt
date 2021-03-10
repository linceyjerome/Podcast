package com.d74.tp2.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.d74.tp2.Models.Podcast

@Dao
interface PodcastDao {
    @Query("SELECT * from Podcasts")
    fun All() : LiveData<List<Podcast>>

    @Query("SELECT * from Podcasts WHERE podcastId = :podcastId")
    fun Find(podcastId: Int): LiveData<List<Podcast>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun Add(p: Podcast)

    @Update
    fun Update(p: Podcast)

    @Query("DELETE FROM Podcasts WHERE podcastId = :podcastId")
    fun Remove(podcastId: Int)

    @Query("SELECT * FROM Podcasts WHERE podcastId = :podcastId")
    fun Single(podcastId: Int): Podcast
}