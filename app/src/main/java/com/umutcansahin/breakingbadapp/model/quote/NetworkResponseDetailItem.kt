package com.umutcansahin.breakingbadapp.model.quote


import com.google.gson.annotations.SerializedName

data class NetworkResponseDetailItem(
    @SerializedName("author")
    val author: String?,
    @SerializedName("quote")
    val quote: String?,
    @SerializedName("quote_id")
    val quoteId: Int?,
    @SerializedName("series")
    val series: String?
)