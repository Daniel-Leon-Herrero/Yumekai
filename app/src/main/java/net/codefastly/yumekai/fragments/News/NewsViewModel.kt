package net.codefastly.yumekai.fragments.News

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.helpers.Handlers.HTTPDataHandler
import net.codefastly.yumekai.models.news.AnimeNewsAndFactsResponse
import net.codefastly.yumekai.models.news.Item

class NewsViewModel: ViewModel() {

    private val _articles = MutableLiveData<List<Item>>()
    val articles : LiveData<List<Item>> get() = _articles

    private val RSS_animeNewsAndFacts = "https://animenewsandfacts.com/category/news/feed/"
    private val RSS2JSON = "https://api.rss2json.com/v1/api.json?rss_url="

    var fetchingData : Boolean = false
    init {
        fetchFeedRSS()
    }

    fun fetchFeedRSS(){
        viewModelScope.launch {
            fetchingData = true
            val result: String
            val http: HTTPDataHandler.HTTPReference = HTTPDataHandler().HTTPReference()
            withContext(Dispatchers.IO){
                result = http.getHTTPDataHandler( RSS2JSON + RSS_animeNewsAndFacts )!!
            }
            if ( result.isEmpty()) {
                fetchingData = false
                return@launch
            }
            val rssArticles: AnimeNewsAndFactsResponse =
                Gson().fromJson<AnimeNewsAndFactsResponse>(result, AnimeNewsAndFactsResponse::class.java!!)
            _articles.value = rssArticles.items
            fetchingData = false
        }
    }

}