package net.codefastly.yumekai.fragments.Recent

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.R
import net.codefastly.yumekai.helpers.LocalDatabase.LocalAnimeDB
import net.codefastly.yumekai.models.recents.ModelDTO
import net.codefastly.yumekai.models.recents.RecentsResponse
import net.codefastly.yumekai.models.recents.Result
import net.codefastly.yumekai.repository.online.repositoryAPI

class RecentViewModel : ViewModel() {
    var recentsData = MutableLiveData<RecentsResponse>()
    private val repo = repositoryAPI()
    private lateinit var _context: Context
    private lateinit var _owner: LifecycleOwner


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

    var history = MutableLiveData<RecentsResponse>(itemList)
    var tv = MutableLiveData<RecentsResponse>(itemList)
    var movies = MutableLiveData<RecentsResponse>(itemList)
    var ova = MutableLiveData<RecentsResponse>(itemList)
    var ona = MutableLiveData<RecentsResponse>(itemList)
    var special = MutableLiveData<RecentsResponse>(itemList)
    var list = MutableLiveData<List<ModelDTO>>()

    init {
        getRecentsData()
        getAllData()
    }

    fun setContext(context: Context, owner: LifecycleOwner) {
        _context = context
        _owner = owner
        setHistoryData()
    }


    fun getRecentsData() {
        var mutableData: MutableLiveData<RecentsResponse> = MutableLiveData()
        repo.getRecents().observeForever(Observer { recents ->
            mutableData.value = recents
        })

        recentsData = mutableData
    }

    fun getAllData(){

        viewModelScope.launch {
            withContext(Dispatchers.Main) {

                repo.getRecentsMovies().observeForever(Observer {
                    movies.value = it
                })
                delay(500)
                repo.getRecentsTV().observeForever(Observer {
                    tv.value = it
                })
                delay(500)
                repo.getRecentsOna().observeForever(Observer {
                    ona.value = it
                })
                delay(500)
                repo.getRecentsOva().observeForever(Observer {
                    ova.value = it
                })
                delay(800)
                repo.getRecentsSpecial().observeForever(Observer {
                    special.value = it
                })
                delay(500)
                refreshData()
            }
        }
    }

    private fun setHistoryData(){
            LocalAnimeDB.getLocalAnimeDB(_context).localAnimeDao().getLocalAnimeHistory().observe(_owner,
                Observer {
                    var result = mutableListOf<Result>()
                    it.forEach {
                        result.add( Result(
                            true,
                            "",
                            300,
                            it.imageUrl,
                            it.mal_id,
                            500,
                            "",
                            50.6,
                            "",
                            "",
                            "",
                            "",
                            ""
                        ))
                    }
                    var his = RecentsResponse(0,0,true,"",result)
                    history.value = his
                    refreshData()
                })
        }

    fun refreshData(){
        list.value = listOf( ModelDTO(
            1,
            "Historial",
            "Last seen anime",
            R.drawable.ic_baseline_navigate_next_24,
            R.color.red_primary,
            "More Historial",
            history.value!!
        ),
            ModelDTO(
                2,
                "TV",
                "Recent TV",
                R.drawable.ic_outline_article_36,
                R.color.red_primary,
                "More TV",
                tv.value!!
            ),

            ModelDTO(
                3,
                "Peliculas",
                "Recent Movies",
                R.drawable.ic_outline_article_36,
                R.color.red_primary,
                "More Peliculas",
                movies.value!!
            ),

            ModelDTO(
                4,
                "OVA",
                "Recent OVA's",
                R.drawable.ic_outline_article_36,
                R.color.red_primary,
                "More OVA",
                ova.value!!
            ),
            ModelDTO(
                5,
                "ONA",
                "Recent ONA's",
                R.drawable.ic_outline_article_36,
                R.color.red_primary,
                "More ONA",
                ona.value!!
            ),
            ModelDTO(
                6,
                "Special",
                "Recent Special's",
                R.drawable.ic_outline_article_36,
                R.color.red_primary,
                "More Special",
                special.value!!
            ),
        )
    }
}