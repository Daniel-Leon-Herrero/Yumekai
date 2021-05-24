package net.codefastly.yumekai.helpers.LocalDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.codefastly.yumekai.interfaces.LocalAnimeHistoryDao
import net.codefastly.yumekai.models.room.LocalAnimeHistory


@Database(entities = arrayOf(LocalAnimeHistory::class), version = 4)
abstract class LocalAnimeDB : RoomDatabase() {
    abstract fun localAnimeDao(): LocalAnimeHistoryDao

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