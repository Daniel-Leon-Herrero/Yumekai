package net.codefastly.yumekai.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import net.codefastly.yumekai.models.room.LocalAnime
import net.codefastly.yumekai.models.room.LocalAnimeHistory

@Dao
interface LocalAnimeDao {

    @Query("SELECT * FROM LocalAnime ORDER BY date DESC")
    fun getLocalAnime(): LiveData<List<LocalAnime>>

    @Insert
    fun insertLocalAnime(animes: LocalAnime)

    @Query("SELECT EXISTS(SELECT * FROM LocalAnime WHERE mal_id = :mal_id )")
    fun getIfExsists(mal_id: Int): Boolean

    @Query("SELECT * FROM LocalAnime WHERE mal_id = :mal_id")
    fun getLocalAnimeByMalid(mal_id: Int): LocalAnime

    @Update
    fun updateLocalAnime(anime: LocalAnime)

    @Delete
    fun deleteLocalAnime(anime:LocalAnime)

}