package net.codefastly.yumekai.models.calendar

import com.google.gson.annotations.SerializedName
import net.codefastly.yumekai.models.anime.Licensor

data class Day(
    @SerializedName("airing_start") val airing_start: String,
    @SerializedName("episodes") val episodes: Int,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("kids") val kids: Boolean,
    @SerializedName("licensors") val licensors: List<String>,
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("members") val members: Int,
    @SerializedName("producers") val producers: List<Producer>,
    @SerializedName("r18") val r18: Boolean,
    @SerializedName("score") val score: Double,
    @SerializedName("source") val source: String,
    @SerializedName("synopsis")  val synopsis: String,
    @SerializedName("title")  val title: String,
    @SerializedName("type")  val type: String,
    @SerializedName("url")   val url: String
)