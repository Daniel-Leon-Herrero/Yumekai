package net.codefastly.yumekai.fragments.Drawer

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
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

    val anime = LocalAnime(
        1,
        "Prueba",
        "Desc",
        ""
    )

    val list = listOf(
        listOf<LocalAnime>(anime, anime, anime, anime, anime, anime),
        listOf<LocalAnime>(anime, anime, anime),
        listOf<LocalAnime>(anime,  anime),
        listOf<LocalAnime>(anime, anime, anime, anime)
    )

    fun attach( fragment: DrawerFragment, context: Context ){
        this._owner = fragment
        this._context = context
    }

    fun fetchMagicDrawerData(){
        LocalAnimeDB.getLocalAnimeDB(_context).localAnime().getLocalAnime().observe( _owner, { animeList ->
            Log.e(TAG, animeList.toString() )
        })
    }


}