package net.codefastly.yumekai.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.interfaces.APIService
import net.codefastly.yumekai.models.AnimeCharacters.CharacterAnimeResponse
import net.codefastly.yumekai.models.anime.AnimeResponse
import net.codefastly.yumekai.models.calendar.AnimeDTO
import net.codefastly.yumekai.models.recents.RecentsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class repositoryAPI {

    private fun getCalendarRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jikan.moe/v3/schedule/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getAnimeRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jikan.moe/v3/anime/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getRecentsRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jikan.moe/v3/search/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getCalenderAnimeMonday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getCalendarRetrofit().create(APIService::class.java)
                    .getCalendarAnimesMonday("$day")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        val calendar: MutableList<AnimeDTO> = arrayListOf()
                        datos?.day?.forEach { day ->
                            var cal = AnimeDTO(day)
                            calendar.add(cal)
                        }

                        mutableData.value = calendar
                    }
                }

            }
        }
        return mutableData
    }

    fun getCalenderAnimeTuesday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getCalendarRetrofit().create(APIService::class.java)
                    .getCalendarAnimesTuesday("$day")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        val calendar: MutableList<AnimeDTO> = arrayListOf()
                        datos?.day?.forEach { day ->
                            var cal = AnimeDTO(day)
                            calendar.add(cal)
                        }

                        mutableData.value = calendar
                    }
                }

            }
        }
        return mutableData
    }

    fun getCalenderAnimeWednesday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getCalendarRetrofit().create(APIService::class.java)
                    .getCalendarAnimesWednesday("$day")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        val calendar: MutableList<AnimeDTO> = arrayListOf()
                        datos?.day?.forEach { day ->
                            var cal = AnimeDTO(day)
                            calendar.add(cal)
                        }

                        mutableData.value = calendar
                    }
                }

            }
        }
        return mutableData
    }

    fun getCalenderAnimeThursday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getCalendarRetrofit().create(APIService::class.java)
                    .getCalendarAnimesThursday("$day")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        val calendar: MutableList<AnimeDTO> = arrayListOf()
                        datos?.day?.forEach { day ->
                            var cal = AnimeDTO(day)
                            calendar.add(cal)
                        }

                        mutableData.value = calendar
                    }
                }

            }
        }
        return mutableData
    }

    fun getCalenderAnimeFriday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getCalendarRetrofit().create(APIService::class.java)
                    .getCalendarAnimesFriday("$day")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        val calendar: MutableList<AnimeDTO> = arrayListOf()
                        datos?.day?.forEach { day ->
                            var cal = AnimeDTO(day)
                            calendar.add(cal)
                        }
                        mutableData.value = calendar
                    }
                }

            }
        }

        return mutableData
    }

    fun getCalenderAnimeSaturday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getCalendarRetrofit().create(APIService::class.java)
                    .getCalendarAnimesSaturday("$day")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        val calendar: MutableList<AnimeDTO> = arrayListOf()
                        datos?.day?.forEach { day ->
                            var cal = AnimeDTO(day)
                            calendar.add(cal)
                        }

                        mutableData.value = calendar
                    }
                }

            }
        }
        return mutableData
    }

    fun getCalenderAnimeSunday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getCalendarRetrofit().create(APIService::class.java)
                    .getCalendarAnimesSunday("$day")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        val calendar: MutableList<AnimeDTO> = arrayListOf()
                        datos?.day?.forEach { day ->
                            var cal = AnimeDTO(day)
                            calendar.add(cal)
                        }

                        mutableData.value = calendar
                    }
                }

            }
        }
        return mutableData
    }

    fun getAnime(id: Int): LiveData<AnimeResponse> {
        var mutableData = MutableLiveData<AnimeResponse>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getAnimeRetrofit().create(APIService::class.java)
                    .getAnimes("$id")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        mutableData.value = datos!!
                    }
                }

            }
        }
        return mutableData
    }

    fun getAnimeCharacter(id: Int): LiveData<CharacterAnimeResponse> {
        var mutableData = MutableLiveData<CharacterAnimeResponse>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getAnimeRetrofit().create(APIService::class.java)
                    .getAnimesCharacter("$id/characters_staff")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        mutableData.value = datos!!
                    }
                }

            }
        }

        return mutableData
    }

    fun getRecents(): LiveData<RecentsResponse> {
        var mutableData = MutableLiveData<RecentsResponse>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
               val call = getRecentsRetrofit().create(APIService::class.java)
                    .getRecents("anime?q=&order_by=members&sort=desc&page=1")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        mutableData.value = datos!!
                    }
                }

            }
        }
        return mutableData
    }

    fun getRecentsMovies(): LiveData<RecentsResponse> {
        var mutableData = MutableLiveData<RecentsResponse>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getRecentsRetrofit().create(APIService::class.java)
                    .getRecents("anime?q=&order_by=members&type=movie&page=1")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        mutableData.value = datos!!
                    }
                }

            }
        }
        return mutableData
    }


}
