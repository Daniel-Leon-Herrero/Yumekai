package net.codefastly.yumekai.models.anime

import com.google.gson.annotations.SerializedName

data class To(
    @SerializedName("day") val day: Any,
    @SerializedName("month") val month: Any,
    @SerializedName("year") val year: Any
)