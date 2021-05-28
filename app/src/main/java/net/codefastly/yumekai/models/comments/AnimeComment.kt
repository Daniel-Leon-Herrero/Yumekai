package net.codefastly.yumekai.models.comments

data class AnimeComment(
    val anime_id: Long,
    val commented_on: String,
    val message: String,
    val reactions: Long,
    val supporter: Boolean,
    val user_image: String,
    val usermame: String
) {
}