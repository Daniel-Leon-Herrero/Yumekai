package net.codefastly.yumekai.fragments.Search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.recents.Result

class SearchViewModel: ViewModel() {

    private val _animeList = MutableLiveData<List<Result>>()
    val animeList : LiveData<List<Result>> get() = _animeList

    fun searchByText( query: String ){

    }

}