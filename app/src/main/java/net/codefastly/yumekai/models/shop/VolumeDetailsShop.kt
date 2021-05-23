package net.codefastly.yumekai.models.shop

data class VolumeDetailsShop(
    val age_rating: String,
    val dimensional_weight: Double,
    val genre: List<String>,
    val language: String,
    val page_count: Long,
    val publisher: String,
    val release_date: String,
    val themes: List<String>
) {
}