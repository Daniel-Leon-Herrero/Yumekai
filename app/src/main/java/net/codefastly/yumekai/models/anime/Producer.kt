package net.codefastly.yumekai.models.anime

import com.google.gson.annotations.SerializedName

data class Producer(
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)