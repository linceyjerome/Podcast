package com.d74.tp2.Models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Episode",foreignKeys = [
        ForeignKey(
                entity = Podcast::class,
                parentColumns = ["podcastId"],
                childColumns = ["FKPodcastId"],
        )])
data class Episode(
        @PrimaryKey(autoGenerate = true)
        val episodeId : Int,

        @NonNull
        val guid : String,

        @NonNull
        val title: String,

        @NonNull
        val description: String,

        @NonNull
        val audio: String,

        @NonNull
        val FKPodcastId: Int
)