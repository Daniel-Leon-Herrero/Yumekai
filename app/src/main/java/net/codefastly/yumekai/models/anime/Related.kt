package net.codefastly.yumekai.models.anime

import com.google.gson.annotations.SerializedName

data class Related(
    @SerializedName("Adaptation") val Adaptation: List<Adaptation>
)