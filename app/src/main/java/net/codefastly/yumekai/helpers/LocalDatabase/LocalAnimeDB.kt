package net.codefastly.yumekai.helpers.LocalDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.codefastly.yumekai.interfaces.LocalAnimeDao
import net.codefastly.yumekai.interfaces.LocalAnimeHistoryDao
import net.codefastly.yumekai.models.room.LocalAnime
import net.codefastly.yumekai.models.room.LocalAnimeHistory


@Database(entities = [LocalAnimeHistory::class, LocalAnime::class], version = 5)
abstract class LocalAnimeDB : RoomDatabase() {
    abstract fun localAnimeHistoryDao(): LocalAnimeHistoryDao
    abstract fun localAnime(): LocalAnimeDao

    companion object{
        @Volatile
        private var INSTANCE: LocalAnimeDB? = null

        fun getLocalAnimeDB(context: Context): LocalAnimeDB{
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this){
                INSTANCE = Room
                    .databaseBuilder(context,LocalAnimeDB::class.java, "LOCAL_ANIME_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }
}