package net.codefastly.yumekai.fragments.Recent

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.recents.RecentsResponse
import net.codefastly.yumekai.models.recents.Result
import net.codefastly.yumekai.repository.repositoryAPI

class RecentViewModel : ViewModel() {

    var result = listOf<Result>(
        Result(
            true,
            "15/5/2021",
            300,
            "aaaaaaa",
            11,
            500,
            "ff",
            50.6,
            "5/5/2021",
            "A jose le toco Beatrice",
            "Jose ama a su beatrice",
            "hentai",
            "sdsdsdsdsd"
        )
    )
    var itemList: RecentsResponse = RecentsResponse(1, 11, true, "dsdsdsdsdsd", result)
    private val repo = repositoryAPI()
    var history: RecentsResponse = itemList
    var tv: RecentsResponse = itemList
    var movies: RecentsResponse = itemList
    var ova: RecentsResponse = itemList
    var ona: RecentsResponse = itemList
    var special: RecentsResponse = itemList
    init {
    }

    fun getRecentsData(): MutableLiveData<RecentsResponse> {
        var mutableData: MutableLiveData<RecentsResponse> = MutableLiveData()
        repo.getRecents().observeForever(Observer { recents ->
            mutableData.value = recents
        })
        return mutableData
    }

    fun getRecentsTVData(): MutableLiveData<RecentsResponse> {
        var mutableData: MutableLiveData<RecentsResponse> = MutableLiveData()
        repo.getRecentsTV().observeForever(Observer { recents ->
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

    fun getRecentsOvaData(): MutableLiveData<RecentsResponse> {
        var mutableData: MutableLiveData<RecentsResponse> = MutableLiveData()
        repo.getRecentsOva().observeForever(Observer { recents ->
            mutableData.value = recents
        })
        return mutableData
    }

    fun getRecentsONAData(): MutableLiveData<RecentsResponse> {
        var mutableData: MutableLiveData<RecentsResponse> = MutableLiveData()
        repo.getRecentsOna().observeForever(Observer { recents ->
            mutableData.value = recents
        })
        return mutableData
    }

    fun getRecentsSpecialData(): MutableLiveData<RecentsResponse> {
        var mutableData: MutableLiveData<RecentsResponse> = MutableLiveData()
        repo.getRecentsSpecial().observeForever(Observer { recents ->
            mutableData.value = recents
        })
        return mutableData
    }
}