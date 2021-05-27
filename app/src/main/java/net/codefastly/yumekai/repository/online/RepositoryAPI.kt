package net.codefastly.yumekai.repository.online

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.activities.DashboardFullScreen.DashboardFullScreen
import net.codefastly.yumekai.interfaces.APIService
import net.codefastly.yumekai.models.AnimeCharacters.CharacterAnimeResponse
import net.codefastly.yumekai.models.anime.AnimeResponse
import net.codefastly.yumekai.models.calendar.*
import net.codefastly.yumekai.models.ranking.TopResponse
import net.codefastly.yumekai.models.recents.RecentsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class repositoryAPI() {

    private lateinit var context: Context
    fun setContext(_context: Context) {
        context = _context
    }

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

    private fun getTopRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jikan.moe/v3/top/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getCalenderAnimeMonday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()
        lateinit var call: Response<CalendarAnimeMondayResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getCalendarRetrofit().create(APIService::class.java)
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
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getCalenderAnimeTuesday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()
        lateinit var call: Response<CalendarAnimeTuesdayResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getCalendarRetrofit().create(APIService::class.java)
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
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getCalenderAnimeWednesday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()
        lateinit var call: Response<CalendarAnimeWednesdayResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getCalendarRetrofit().create(APIService::class.java)
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
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getCalenderAnimeThursday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()
        lateinit var call: Response<CalendarAnimeThursdayResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getCalendarRetrofit().create(APIService::class.java)
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
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getCalenderAnimeFriday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()
        lateinit var call: Response<CalendarAnimeFridayResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getCalendarRetrofit().create(APIService::class.java)
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
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }

        return mutableData
    }

    fun getCalenderAnimeSaturday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()
        lateinit var call: Response<CalendarAnimeSaturdayResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getCalendarRetrofit().create(APIService::class.java)
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
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getCalenderAnimeSunday(day: String): LiveData<MutableList<AnimeDTO>> {
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()
        lateinit var call: Response<CalendarAnimeSundayResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getCalendarRetrofit().create(APIService::class.java)
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
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getAnime(id: Int): LiveData<AnimeResponse> {
        var mutableData = MutableLiveData<AnimeResponse>()
        lateinit var call: Response<AnimeResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getAnimeRetrofit().create(APIService::class.java)
                        .getAnimes("$id")
                    val datos = call.body()
                    withContext(Dispatchers.Main) {
                        if (call.isSuccessful) {
                            mutableData.value = datos!!
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getAnimeCharacter(id: Int): LiveData<CharacterAnimeResponse> {
        var mutableData = MutableLiveData<CharacterAnimeResponse>()
        lateinit var call: Response<CharacterAnimeResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getAnimeRetrofit().create(APIService::class.java)
                        .getAnimesCharacter("$id/characters_staff")
                    val datos = call.body()
                    withContext(Dispatchers.Main) {
                        if (call.isSuccessful) {
                            mutableData.value = datos!!
                        }
                    }
                } catch (e: Exception) {

                }
            }
        }

        return mutableData
    }

    fun getRecents(): LiveData<RecentsResponse> {
        var mutableData = MutableLiveData<RecentsResponse>()
        lateinit var call: Response<RecentsResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getRecentsRetrofit().create(APIService::class.java)
                        .getRecents("anime?status=airing&order_by=members")
                    val datos = call.body()
                    withContext(Dispatchers.Main) {
                        if (call.isSuccessful) {
                            mutableData.value = datos!!
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getRecentsTV(): LiveData<RecentsResponse> {
        var mutableData = MutableLiveData<RecentsResponse>()
        lateinit var call: Response<RecentsResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getRecentsRetrofit().create(APIService::class.java)
                        .getRecents("anime?status=airing&type=TV&order_by=start_date")
                    val datos = call.body()
                    withContext(Dispatchers.Main) {
                        if (call.isSuccessful) {
                            mutableData.value = datos!!
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getRecentsMovies(): LiveData<RecentsResponse> {
        var mutableData = MutableLiveData<RecentsResponse>()
        lateinit var call: Response<RecentsResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getRecentsRetrofit().create(APIService::class.java)
                        .getRecents("anime?type=movie&order_by=start_date&page=1")
                    val datos = call.body()
                    withContext(Dispatchers.Main) {
                        if (call.isSuccessful) {
                            mutableData.value = datos!!
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getRecentsOva(): LiveData<RecentsResponse> {
        var mutableData = MutableLiveData<RecentsResponse>()
        lateinit var call: Response<RecentsResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getRecentsRetrofit().create(APIService::class.java)
                        .getRecents("anime?status=airing&type=ova&order_by=start_date&page=1")
                    val datos = call.body()
                    withContext(Dispatchers.Main) {
                        if (call.isSuccessful) {
                            mutableData.value = datos!!
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getRecentsOna(): LiveData<RecentsResponse> {
        var mutableData = MutableLiveData<RecentsResponse>()
        lateinit var call: Response<RecentsResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getRecentsRetrofit().create(APIService::class.java)
                        .getRecents("anime?status=airing&type=ona&order_by=start_date&page=1")
                    val datos = call.body()
                    withContext(Dispatchers.Main) {
                        if (call.isSuccessful) {
                            mutableData.value = datos!!
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun getRecentsSpecial(): LiveData<RecentsResponse> {
        var mutableData = MutableLiveData<RecentsResponse>()
        lateinit var call: Response<RecentsResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getRecentsRetrofit().create(APIService::class.java)
                        .getRecents("anime?status=airing&type=special&order_by=start_date&page=1")
                    val datos = call.body()
                    withContext(Dispatchers.Main) {
                        if (call.isSuccessful) {
                            mutableData.value = datos!!
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }


    fun searchDataByQuery(query: String, queryCategory: String): LiveData<RecentsResponse> {
        var mutableData = MutableLiveData<RecentsResponse>()
        lateinit var call: Response<RecentsResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getRecentsRetrofit().create(APIService::class.java)
                        .searchAnimeByQuery("${queryCategory}?q=${query}&page=1")
                    val datos = call.body()
                    withContext(Dispatchers.Main) {
                        if (call.isSuccessful) {
                            mutableData.value = datos!!
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }

    fun GettopData(tag: String): LiveData<TopResponse> {
        var mutableData = MutableLiveData<TopResponse>()
        lateinit var call: Response<TopResponse>
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    call = getTopRetrofit().create(APIService::class.java)
                        .topData("${tag}")
                    val datos = call.body()
                    withContext(Dispatchers.Main) {
                        if (call.isSuccessful) {
                            mutableData.value = datos!!
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val intent = Intent(context, DashboardFullScreen::class.java).apply {
                            this.putExtra("FULL_SCREEN_TO_LOAD", 1150)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
        return mutableData
    }


}
