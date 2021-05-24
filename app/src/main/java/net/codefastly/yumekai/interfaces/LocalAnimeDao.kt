package net.codefastly.yumekai.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import net.codefastly.yumekai.models.room.LocalAnime

@Dao
interface LocalAnimeDao {
    @Query("SELECT * FROM LocalAnime ORDER BY date")
    fun getLocalAnime(): LiveData<List<LocalAnime>>

    @Query("SELECT * FROM LocalAnime WHERE title = :title")
    fun getLocalAnimeByName(title: String): LiveData<List<LocalAnime>>

    @Query("SELECT * FROM LocalAnime  ORDER BY date LIMIT 1")
    fun getLastAnime():LocalAnime

    @Query("SELECT count(*) FROM LocalAnime  ORDER BY date LIMIT 1")
    fun getSize(): Int

    @Query("SELECT EXISTS(SELECT * FROM LocalAnime WHERE mal_id = :mal_id )")
    fun getIfExsists(mal_id: Int): Boolean

    @Update
    fun updateLocalAnime(anime: LocalAnime)

    @Insert
    fun insertLocalAnime(animes: LocalAnime)

    @Delete
    fun deleteLocalAnime(anime:LocalAnime)
}