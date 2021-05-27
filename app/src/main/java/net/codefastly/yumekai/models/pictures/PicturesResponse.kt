package net.codefastly.yumekai.models.pictures

data class PicturesResponse(
    val pictures: List<Picture>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String
)