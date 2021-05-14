package net.codefastly.yumekai.models

import com.google.gson.annotations.SerializedName
import net.codefastly.yumekai.R
import java.util.*

data class CalendarAnimeSaturdayResponse(

    @SerializedName("saturday")  val day: List<Day>,
    @SerializedName("request_cache_expiry") val request_cache_expiry: Int,
    @SerializedName("request_cached")  val request_cached: Boolean,
    @SerializedName("request_hash") val request_hash: String
)