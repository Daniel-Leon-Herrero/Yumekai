package net.codefastly.yumekai.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.interfaces.APIService
import net.codefastly.yumekai.models.calendar.AnimeDTO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class repositoryAPI {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jikan.moe/v3/schedule/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getCalenderAnimeMonday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getRetrofit().create(APIService::class.java)
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
                val call = getRetrofit().create(APIService::class.java)
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
                val call = getRetrofit().create(APIService::class.java)
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
                val call = getRetrofit().create(APIService::class.java)
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
                val call = getRetrofit().create(APIService::class.java)
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
                val call = getRetrofit().create(APIService::class.java)
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
                val call = getRetrofit().create(APIService::class.java)
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
}
