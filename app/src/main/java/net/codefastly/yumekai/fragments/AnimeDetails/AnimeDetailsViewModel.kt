package net.codefastly.yumekai.fragments.AnimeDetails

import androidx.lifecycle.*
import net.codefastly.yumekai.models.AnimeCharacters.CharacterAnimeResponse
import net.codefastly.yumekai.models.anime.AnimeResponse
import net.codefastly.yumekai.repository.online.repositoryAPI

class AnimeDetailsViewModel : ViewModel() {
    private val repo = repositoryAPI()
    var anime: MutableLiveData<Int> = MutableLiveData()
    var animeDetails: MutableLiveData<AnimeResponse> = MutableLiveData()
    var animeCharacter: MutableLiveData<CharacterAnimeResponse> = MutableLiveData()

    private val _fetching = MutableLiveData<Int>()
    val fetching : LiveData<Int> get() = _fetching

    init {
        anime.observeForever(Observer {
            getAnime(it)
        })
    }

    fun getAnime(anime: Int) {
        _fetching.value = 0
        repo.getAnime(anime).observeForever { animes ->
            _fetching.value = _fetching.value!! + 1
            animeDetails.value = animes
        }
        repo.getAnimeCharacter(anime).observeForever{ staff ->
            _fetching.value = _fetching.value!! + 1
            animeCharacter.value = staff
        }

    }
}