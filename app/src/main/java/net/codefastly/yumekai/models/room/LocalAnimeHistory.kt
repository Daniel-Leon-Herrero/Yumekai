package net.codefastly.yumekai.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalAnimeHistory(
    @PrimaryKey()
    val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val imageUrl: String,
    val date: Long = System.currentTimeMillis()
)
