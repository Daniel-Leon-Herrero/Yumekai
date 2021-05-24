package net.codefastly.yumekai.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import net.codefastly.yumekai.models.room.LocalAnimeHistory

@Dao
interface LocalAnimeHistoryDao {

    @Query("SELECT * FROM LocalAnimeHistory ORDER BY date DESC")
    fun getLocalAnimeHistory(): LiveData<List<LocalAnimeHistory>>

    @Query("SELECT * FROM LocalAnimeHistory WHERE title = :title")
    fun getLocalAnimeHistoryByName(title: String): LiveData<List<LocalAnimeHistory>>

    @Query("SELECT * FROM LocalAnimeHistory  ORDER BY date LIMIT 1")
    fun getLastAnimeHistory():LocalAnimeHistory

    @Query("SELECT count(*) FROM LocalAnimeHistory  ORDER BY date LIMIT 1")
    fun getSize(): Int

    @Query("SELECT EXISTS(SELECT * FROM LocalAnimeHistory WHERE mal_id = :mal_id )")
    fun getIfExsistsHistory(mal_id: Int): Boolean

    @Update
    fun updateLocalAnimeHistory(animeHistory: LocalAnimeHistory)

    @Insert
    fun insertLocalAnimeHistory(animes: LocalAnimeHistory)

    @Delete
    fun deleteLocalAnimeHistory(animeHistory:LocalAnimeHistory)
}