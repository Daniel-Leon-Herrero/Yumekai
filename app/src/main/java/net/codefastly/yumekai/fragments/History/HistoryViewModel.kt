package net.codefastly.yumekai.fragments.History

import android.content.Context
import androidx.lifecycle.*
import net.codefastly.yumekai.helpers.LocalDatabase.LocalAnimeDB
import net.codefastly.yumekai.models.room.LocalAnime

class HistoryViewModel: ViewModel() {
    private var _historyItems =  MutableLiveData<List<LocalAnime>>()
    val historyItems : LiveData<List<LocalAnime>> get() = _historyItems
    private lateinit var _owner: LifecycleOwner
    private lateinit var _context : Context

    fun setFetch(context: Context, fragment: HistoryFragment){
        _context = context
        _owner = fragment
        LocalAnimeDB.getLocalAnimeDB(_context).localAnimeDao().getLocalAnime().observe(_owner,
            Observer {
                _historyItems.value = it
            })
    }
}