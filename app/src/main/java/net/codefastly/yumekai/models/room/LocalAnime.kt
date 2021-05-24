package net.codefastly.yumekai.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalAnime(
    @PrimaryKey()
    val mal_id: Int,
    val title: String,
    val synopsis: String,
    val imageUrl: String,
    val date: Long = System.currentTimeMillis(),
    var finalized: Boolean = false,
    var favorite: Boolean = false,
    var recommended: Boolean = false,
    var folowing: Boolean = false
)
