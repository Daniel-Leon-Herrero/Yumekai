package net.codefastly.yumekai.fragments.Search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.recents.Result
import net.codefastly.yumekai.repository.repositoryAPI

class SearchViewModel: ViewModel() {

    private val repo = repositoryAPI()

    private val _animeList = MutableLiveData<List<Result>>()
    val animeList : LiveData<List<Result>> get() = _animeList

    var fetchingData: Boolean = false

    fun fetchAnimeByQuery( query: String ){
        fetchingData = true;
        repo.searchAnimeByQuery(query).observeForever { dataResponse ->
            fetchingData = false
            _animeList.value = dataResponse.results
        }
    }

}