package net.codefastly.yumekai.fragments.Drawer

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.R
import net.codefastly.yumekai.helpers.LocalDatabase.LocalAnimeDB
import net.codefastly.yumekai.models.room.LocalAnime

class DrawerViewModel: ViewModel() {

    private lateinit var _owner: LifecycleOwner
    private lateinit var _context: Context

    val tabList = listOf<String>(
        "Finalized",
        "Recommended",
        "Following",
        "Favorites"
    )

    private val _animes = MutableLiveData<List<List<LocalAnime>>>()
    val animes : LiveData<List<List<LocalAnime>>> get() = _animes



    fun attach( fragment: DrawerFragment, context: Context ){
        this._owner = fragment
        this._context = context
    }

    fun fetchMagicDrawerData(){
        LocalAnimeDB.getLocalAnimeDB(_context).localAnime().getLocalAnime().observe( _owner, { animeList ->
            var listFinalized = mutableListOf<LocalAnime>()
            var listRecommended = mutableListOf<LocalAnime>()
            var listFollowing = mutableListOf<LocalAnime>()
            var listFavorites = mutableListOf<LocalAnime>()

            animeList.sortedBy { it.date }

            animeList.forEach { anime ->
                if( anime.finalized ) listFinalized.add( anime )
                if( anime.recommended ) listRecommended.add( anime )
                if( anime.following ) listFollowing.add( anime )
                if( anime.favorite ) listFavorites.add( anime )
            }

            this._animes.value = listOf( listFinalized, listRecommended, listFollowing, listFavorites)

        })
    }


}