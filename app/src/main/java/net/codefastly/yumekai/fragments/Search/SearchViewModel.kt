package net.codefastly.yumekai.fragments.Search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.recents.Result
import net.codefastly.yumekai.repository.repositoryAPI

class SearchViewModel: ViewModel() {

    private val repo = repositoryAPI()

    private val _dataList = MutableLiveData<List<Result>>()
    val dataList : LiveData<List<Result>> get() = _dataList

    var fetchingData: Boolean = false

    fun fetchAnimeByQuery( query: String, queryCategory: String ){
        fetchingData = true;
        repo.searchDataByQuery(query, queryCategory).observeForever { dataResponse ->
            fetchingData = false
            _dataList.value = dataResponse.results
        }
    }



}