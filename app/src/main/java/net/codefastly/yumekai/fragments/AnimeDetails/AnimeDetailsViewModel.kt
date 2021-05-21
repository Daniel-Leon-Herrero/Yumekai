package net.codefastly.yumekai.fragments.AnimeDetails

import android.util.Log
import androidx.lifecycle.*
import net.codefastly.yumekai.models.AnimeCharacters.CharacterAnimeResponse
import net.codefastly.yumekai.models.anime.AnimeResponse
import net.codefastly.yumekai.models.calendar.AnimeDTO
import net.codefastly.yumekai.repository.repositoryAPI

class AnimeDetailsViewModel : ViewModel() {
    private val repo = repositoryAPI()
    var anime: MutableLiveData<Int> = MutableLiveData()
    var animeDetails: MutableLiveData<AnimeResponse> = MutableLiveData()
    var animeCharacter: MutableLiveData<CharacterAnimeResponse> = MutableLiveData()


    init {
        anime.observeForever(Observer {
            getAnime(it)
        })
    }

    fun getAnime(anime: Int) {

        repo.getAnime(anime).observeForever { animes ->
            animeDetails.value = animes
        }
        repo.getAnimeCharacter(anime).observeForever{ staff ->
            animeCharacter.value = staff
        }

    }
}