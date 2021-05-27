package net.codefastly.yumekai.fragments.ranking

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.ranking.RankingTag
import net.codefastly.yumekai.models.ranking.TopResponse
import net.codefastly.yumekai.repository.online.repositoryAPI
import net.codefastly.yumekai.utilities.getRepoAPI

class RankingViewModel : ViewModel() {
    private val repo = getRepoAPI()
    var itemTag =
        listOf<RankingTag>(RankingTag(0, "Anime", 1, "anime"), RankingTag(1, "Manga", 1, "manga"))
    private var _topItems = MutableLiveData<TopResponse>()
    val topItems : LiveData<TopResponse> get() = _topItems
    val mapTop = mutableMapOf<Int,TopResponse>()
    private lateinit var _owner: LifecycleOwner


    fun fetchData(owner: LifecycleOwner) {
        _owner = owner
    }

    fun getData(position: Int): LiveData<TopResponse> {
        if(mapTop[position] == null) {
            repo.GettopData(itemTag[position].tagUrl).observe(_owner, Observer {
                mapTop.set(position, it)
                _topItems.value = mapTop[position]
            })
        }else{
            _topItems.value = mapTop[position]
        }
            return topItems

    }


}