package com.d74.tp2.Models

import com.google.gson.annotations.SerializedName

data class SearchResultItem(
    val collectionId: Int,
    val collectionName: String,
    val artistName: String,

    @SerializedName("artworkUrl100")
    val artworkThumbnailUrl: String,
    @SerializedName("artworkUrl600")
    val artworkUrl: String,

    val country: String,
    val feedUrl: String,
    val genres: List<String>,
    val releaseDate: String,
    val primaryGenreName: String,
    val trackCount: Int
)

/*

 val trackExplicitness: String,
 val trackViewUrl: String,
val contentAdvisoryRating: String,
    val collectionExplicitness: String,
 val trackId: Int,
    val trackName: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
val artistViewUrl: String,
val collectionViewUrl: String,
    val wrapperType: String
    val kind: String,
    val trackHdPrice: Int,
    val collectionPrice: Double,
    val genreIds: List<String>,
    val trackRentalPrice: Int,
    val trackPrice: Double,
    val trackHdRentalPrice: Int,
    val artistId: Int,
    val trackCensoredName: String,
    val currency: String,

 val collectionCensoredName: String,
 val collectionHdPrice: Int,






 */
