package net.codefastly.yumekai.models.shop

data class VolumeShop(
    val description: String,
    val details: VolumeDetailsShop?,
    val image_url: String,
    val price: Double,
    val price_rtl: Double,
    val serie: String,
    val start_description: String,
    val title: String,
    val volume: Long
) {

}