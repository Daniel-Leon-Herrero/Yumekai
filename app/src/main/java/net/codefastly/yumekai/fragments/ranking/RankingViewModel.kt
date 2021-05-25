package net.codefastly.yumekai.fragments.ranking

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.ranking.RankingTag
import net.codefastly.yumekai.models.ranking.TopResponse
import net.codefastly.yumekai.repository.online.repositoryAPI

class RankingViewModel: ViewModel() {
    private val repo = repositoryAPI()
    var itemTag = listOf<RankingTag>(RankingTag("Anime",1,"anime"), RankingTag("Manga", 1,"/manga"))
    private var _topItems = MutableLiveData<TopResponse>()
    val topItems : LiveData<TopResponse> get() = _topItems
    private lateinit var _owner: LifecycleOwner

    fun fetchData(owner: LifecycleOwner){
        _owner = owner
    }

    private fun getData(){
        repo.GettopData(itemTag[0].tagUrl).observe(_owner, Observer {
            _topItems.value = it
        })
    }


}