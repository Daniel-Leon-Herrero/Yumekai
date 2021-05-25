package net.codefastly.yumekai.models.ranking

import com.google.gson.annotations.SerializedName

data class TopResponse(
    @SerializedName("top") val top: List<Top>
)