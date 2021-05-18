package net.codefastly.yumekai.fragments.AnimeDetails

import android.util.Log
import androidx.lifecycle.*
import net.codefastly.yumekai.models.AnimeCharacters.CharacterAnimeResponse
import net.codefastly.yumekai.models.anime.AnimeResponse
import net.codefastly.yumekai.models.calendar.AnimeDTO
import net.codefastly.yumekai.repository.repositoryAPI

class AnimeDetailsViewModel : ViewModel() {
    private val repo = repositoryAPI()
    var anime: MutableLiveData<AnimeDTO> = MutableLiveData()
    var animeDetails: MutableLiveData<AnimeResponse> = MutableLiveData()
    var animeCharacter: MutableLiveData<CharacterAnimeResponse> = MutableLiveData()


    init {
        anime.observeForever(Observer {
            getAnime()
        })
    }

    fun getAnime() {

        repo.getAnime(anime.value?.day!!.mal_id).observeForever { animes ->
            animeDetails.value = animes
        }
        repo.getAnimeCharacter(anime.value?.day!!.mal_id).observeForever{ staff ->
            animeCharacter.value = staff
        }

    }
}