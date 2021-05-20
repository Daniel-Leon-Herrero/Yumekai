package net.codefastly.yumekai.fragments.Recent

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.recents.RecentsResponse
import net.codefastly.yumekai.repository.repositoryAPI

class RecentViewModel : ViewModel() {
    private val repo = repositoryAPI()
    var history: RecentsResponse? = null
    var movies: RecentsResponse? = null
    init {
    }

    fun getRecentsData(): MutableLiveData<RecentsResponse> {
        var mutableData: MutableLiveData<RecentsResponse> = MutableLiveData()
        repo.getRecents().observeForever(Observer { recents ->
            mutableData.value = recents
        })
        return mutableData
    }

    fun getRecentsMoviesData(): MutableLiveData<RecentsResponse> {
        var mutableData: MutableLiveData<RecentsResponse> = MutableLiveData()
        repo.getRecentsMovies().observeForever(Observer { recents ->
            mutableData.value = recents
        })
        return mutableData
    }
}