package net.codefastly.yumekai.fragments.AnimeDetails

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.helpers.LocalDatabase.LocalAnimeDB
import net.codefastly.yumekai.models.AnimeCharacters.CharacterAnimeResponse
import net.codefastly.yumekai.models.anime.AnimeResponse
import net.codefastly.yumekai.models.room.LocalAnimeHistory
import net.codefastly.yumekai.repository.online.repositoryAPI
import net.codefastly.yumekai.utilities.getRepoAPI

class AnimeDetailsViewModel() : ViewModel() {
    private val repo = getRepoAPI()
    var anime: MutableLiveData<Int> = MutableLiveData()
    var animeDetails: MutableLiveData<AnimeResponse> = MutableLiveData()
    var animeCharacter: MutableLiveData<CharacterAnimeResponse> = MutableLiveData()
    private lateinit var _context: Context
    private lateinit var _owner: LifecycleOwner


    private val _fetching = MutableLiveData<Int>()
    val fetching: LiveData<Int> get() = _fetching

    init {
        anime.observeForever(Observer {
            getAnime(it)
        })
    }

    fun setAttach(context: Context, fragment: AnimeDetailsFragment) {
        _context = context
        _owner = fragment
    }

    fun getAnime(anime: Int) {
        _fetching.value = 0
        repo.getAnime(anime).observeForever { animes ->
            _fetching.value = _fetching.value!! + 1
            animeDetails.value = animes
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    localData()
                }
            }
        }
        repo.getAnimeCharacter(anime).observeForever { staff ->
            _fetching.value = _fetching.value!! + 1
            animeCharacter.value = staff
        }

    }

    private fun localData() {
        if (animeDetails != null) {
            with(LocalAnimeDB.getLocalAnimeDB(_context).localAnimeHistoryDao()) {
                if (_context != null) {
                    if (!getIfExsistsHistory(animeDetails.value!!.mal_id)) {
                        LocalAnimeDB.getLocalAnimeDB(_context).localAnimeHistoryDao().insertLocalAnimeHistory(
                            LocalAnimeHistory(
                                animeDetails.value!!.mal_id,
                                animeDetails.value!!.title,
                                animeDetails.value?.synopsis,
                                animeDetails.value!!.image_url,
                            )
                        )
                    }else{
                        LocalAnimeDB.getLocalAnimeDB(_context).localAnimeHistoryDao().updateLocalAnimeHistory(
                            LocalAnimeHistory(
                                animeDetails.value!!.mal_id,
                                animeDetails.value!!.title,
                                animeDetails.value?.synopsis,
                                animeDetails.value!!.image_url,
                            )
                        )
                    }
                    if(getSize() > 20){
                        deleteLastAnime()
                    }
                }
            }
        }
    }
    private fun deleteLastAnime(){
        with(LocalAnimeDB.getLocalAnimeDB(_context).localAnimeHistoryDao()) {
            deleteLocalAnimeHistory(getLastAnimeHistory())
        }
    }
}


