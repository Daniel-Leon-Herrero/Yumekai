package net.codefastly.yumekai.models.shop

data class Volume (
        val title: String,
        val volume: Int,
        val image_url: String,
        val rtl_price: Float,
        val price: Float
        ){}