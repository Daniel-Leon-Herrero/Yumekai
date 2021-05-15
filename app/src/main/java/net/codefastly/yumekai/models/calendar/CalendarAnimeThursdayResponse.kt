package net.codefastly.yumekai.models.calendar

import com.google.gson.annotations.SerializedName

data class CalendarAnimeThursdayResponse(

    @SerializedName("thursday")  val day: List<Day>,
    @SerializedName("request_cache_expiry") val request_cache_expiry: Int,
    @SerializedName("request_cached")  val request_cached: Boolean,
    @SerializedName("request_hash") val request_hash: String
)