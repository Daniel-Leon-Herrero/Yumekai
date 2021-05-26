package net.codefastly.yumekai.fragments.AnimeDetails

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.helpers.LocalDatabase.LocalAnimeDB
import net.codefastly.yumekai.models.AnimeCharacters.CharacterAnimeResponse
import net.codefastly.yumekai.models.anime.AnimeResponse
import net.codefastly.yumekai.models.room.LocalAnimeHistory
import net.codefastly.yumekai.repository.online.repositoryAPI

class AnimeDetailsViewModel() : ViewModel() {
    private val repo = repositoryAPI()
    var animeDetails: MutableLiveData<AnimeResponse> = MutableLiveData()
    var animeCharacter: MutableLiveData<CharacterAnimeResponse> = MutableLiveData()
    private lateinit var _context: Context
    private lateinit var _owner: LifecycleOwner


    private val _fetching = MutableLiveData<Int>()
    val fetching: LiveData<Int> get() = _fetching


    fun setAttach(context: Context, fragment: AnimeDetailsFragment) {
        _context = context
        _owner = fragment
    }


    fun fetchAnimeDetails( animeId: Int ){
        _fetching.value = 0
        repo.getAnime(animeId).observe( _owner, { animeResp ->
            _fetching.value = _fetching.value!! + 1
            animeDetails.value = animeResp
            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    localData()
                }
            }
        })
        repo.getAnimeCharacter(animeId).observe(_owner, { characterResp ->
            _fetching.value = _fetching.value!! + 1
            animeCharacter.value = characterResp
        })
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


