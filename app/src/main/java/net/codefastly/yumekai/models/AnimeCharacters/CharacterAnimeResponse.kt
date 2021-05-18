package net.codefastly.yumekai.models.AnimeCharacters

data class CharacterAnimeResponse(
    val characters: List<Character>,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val staff: List<Staff>
)