package net.codefastly.yumekai.fragments.AnimeDetails

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.helpers.LocalDatabase.LocalAnimeDB
import net.codefastly.yumekai.models.AnimeCharacters.CharacterAnimeResponse
import net.codefastly.yumekai.models.anime.AnimeResponse
import net.codefastly.yumekai.models.comments.AnimeComment
import net.codefastly.yumekai.models.pictures.Picture
import net.codefastly.yumekai.models.room.LocalAnimeHistory
import net.codefastly.yumekai.repository.online.RepositoryFirebase
import net.codefastly.yumekai.repository.online.repositoryAPI

class AnimeDetailsViewModel() : ViewModel() {
    private val repo = repositoryAPI()
    private val repoFirebase = RepositoryFirebase()

    private val _anime = MutableLiveData<AnimeResponse>()
    val anime: LiveData<AnimeResponse> get() = _anime

    private val _characterAndStaff = MutableLiveData<CharacterAnimeResponse>()
    val characterAndStaff: LiveData<CharacterAnimeResponse> get() = _characterAndStaff

    private val _pictures = MutableLiveData<List<Picture>>()
    val pictures : LiveData<List<Picture>> get() = _pictures


    private val _comments = MutableLiveData<List<AnimeComment>>()
    val comments : LiveData<List<AnimeComment>> get() = _comments


    private lateinit var _context: Context
    private lateinit var _owner: LifecycleOwner

    var tabList = mapOf<Int, String>(
        0 to "General",
        1 to "Media",
        2 to "Galery",
        3 to "Comments"
    )


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
            _anime.value = animeResp
            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    localData()
                }
            }
        })
        repo.getAnimeCharacter(animeId).observe(_owner, { characterResp ->
            _fetching.value = _fetching.value!! + 1
            _characterAndStaff.value = characterResp
        })
    }

    fun fetchAnimePictures( animeId: Int ){
        repo.getAnimePictures( animeId ).observe( _owner, { picturesResponse ->
            _pictures.value = picturesResponse.pictures
        })
    }

    fun fetchCommentsByAnimeId( animeId: Int ){
        viewModelScope.launch {
            repoFirebase.getRealTimeCommentsByAnimeId( animeId ).observe( _owner, { messageList ->
                if ( _comments.value === null ){
                    _comments.value = messageList
                }else{
                    val newList = mutableListOf<AnimeComment>()
                    newList.addAll( comments.value!! )
                    newList.addAll( messageList )
                    _comments.postValue( newList )
                }
            })
        }
    }

    fun loadFirebaseComment( message: HashMap<String, Any> ){
        repoFirebase.addComments(message)
    }

    private fun localData() {
        if (_anime != null) {
            with(LocalAnimeDB.getLocalAnimeDB(_context).localAnimeHistoryDao()) {
                if (_context != null) {
                    if (!getIfExsistsHistory(_anime.value!!.mal_id)) {
                        LocalAnimeDB.getLocalAnimeDB(_context).localAnimeHistoryDao().insertLocalAnimeHistory(
                            LocalAnimeHistory(
                                _anime.value!!.mal_id,
                                _anime.value!!.title,
                                _anime.value?.synopsis,
                                _anime.value!!.image_url,
                            )
                        )
                    }else{
                        LocalAnimeDB.getLocalAnimeDB(_context).localAnimeHistoryDao().updateLocalAnimeHistory(
                            LocalAnimeHistory(
                                _anime.value!!.mal_id,
                                _anime.value!!.title,
                                _anime.value?.synopsis,
                                _anime.value!!.image_url,
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
