package com.d74.tp2.Models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
        tableName = "Podcasts",
        indices = [Index(value = ["nom"], unique = true),
                Index(value = ["feedUrl"],unique = true)]
)
data class Podcast(
        @PrimaryKey
        val podcastId : Int,

        @NonNull
        val nom: String,

        @NonNull
        val auteur: String,

        @NonNull
        val imgUrl: String,

        @NonNull
        val feedUrl: String
)