package net.codefastly.yumekai.models.recents

import com.google.gson.annotations.SerializedName

data class RecentsResponse(
    @SerializedName("last_page") val last_page: Int,
    @SerializedName("request_cache_expiry") val request_cache_expiry: Int,
    @SerializedName("request_cached") val request_cached: Boolean,
    @SerializedName("request_hash") val request_hash: String,
    @SerializedName("results") val results: List<Result>
)