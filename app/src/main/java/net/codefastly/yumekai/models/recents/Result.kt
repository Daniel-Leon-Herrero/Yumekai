package net.codefastly.yumekai.models.recents

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("airing") val airing: Boolean,
    @SerializedName("end_date") val end_date: Any,
    @SerializedName("episodes") val episodes: Int,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("members") val members: Int,
    @SerializedName("rated") val rated: String,
    @SerializedName("score") val score: Double,
    @SerializedName("start_date") val start_date: String,
    @SerializedName("synopsis") val synopsis: String,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)