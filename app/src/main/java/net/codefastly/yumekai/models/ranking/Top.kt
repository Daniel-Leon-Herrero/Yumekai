package net.codefastly.yumekai.models.ranking

import com.google.gson.annotations.SerializedName

data class Top(
    @SerializedName("end_date") val end_date: String,
    @SerializedName("episodes") val episodes: Int,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("members") val members: Int,
    @SerializedName("rank") val rank: Int,
    @SerializedName("score") val score: Double,
    @SerializedName("start_date") val start_date: String,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)