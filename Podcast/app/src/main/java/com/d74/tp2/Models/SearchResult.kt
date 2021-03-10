package com.d74.tp2.Models

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("resultCount")
    val searchResultCount: Int,
    @SerializedName("results")
    val searchResultItems: List<SearchResultItem>
)