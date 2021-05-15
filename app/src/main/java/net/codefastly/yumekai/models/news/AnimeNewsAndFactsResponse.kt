package net.codefastly.yumekai.models.news

data class AnimeNewsAndFactsResponse(
    val feed: Feed,
    val items: List<Item>,
    val status: String
)