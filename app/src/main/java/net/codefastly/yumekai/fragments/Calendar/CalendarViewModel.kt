package net.codefastly.yumekai.fragments.Calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.calendar.AnimeDTO
import net.codefastly.yumekai.repository.online.repositoryAPI
import java.util.*

class CalendarViewModel : ViewModel() {
    private val repo = repositoryAPI()
    var day: MutableLiveData<String> = MutableLiveData("")
    val dayOfWeek = arrayOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")
    var anime: MutableLiveData<MutableList<AnimeDTO>> = MutableLiveData()

    init {
        obtainDayOfWeek()
        day.observeForever(androidx.lifecycle.Observer {
            searchByDay()
        })
    }

    fun obtainDayOfWeek() {
        var dayString: String = ""
        var calendar: Calendar = Calendar.getInstance()
        var dayInt: Int = calendar.get(Calendar.DAY_OF_WEEK)

        when (dayInt) {
            Calendar.SUNDAY -> dayString = "sunday"
            Calendar.MONDAY -> dayString = "monday"
            Calendar.TUESDAY -> dayString = "tuesday"
            Calendar.WEDNESDAY -> dayString = "wednesday"
            Calendar.THURSDAY -> dayString = "thursday"
            Calendar.FRIDAY -> dayString = "friday"
            Calendar.SATURDAY -> dayString = "saturday"
        }
        day.value = dayString
    }



    fun searchByDay() {
        //Guardar next y prev
        val mutableData = MutableLiveData<MutableList<AnimeDTO>>()
            when (day.value!!) {
                "monday" -> repo.getCalenderAnimeMonday(day.value!!).observeForever { animes ->
                    mutableData.value = animes
                }
                "tuesday" -> repo.getCalenderAnimeTuesday(day.value!!).observeForever { animes ->
                    mutableData.value = animes
                }
                "wednesday" -> repo.getCalenderAnimeWednesday(day.value!!).observeForever { animes ->
                    mutableData.value = animes
                }

                "thursday" -> repo.getCalenderAnimeThursday(day.value!!).observeForever { animes ->
                    mutableData.value = animes
                }

                "friday" -> repo.getCalenderAnimeFriday(day.value!!).observeForever { animes ->
                    mutableData.value = animes
                }

                "saturday" -> repo.getCalenderAnimeSaturday(day.value!!).observeForever { animes ->
                    mutableData.value = animes
                }

                "sunday" -> repo.getCalenderAnimeSunday(day.value!!).observeForever { animes ->
                    mutableData.value = animes
                }
        }
        anime = mutableData
    }

    fun previusDay(day: String):String{
        var actual: Int = dayOfWeek.indexOf(day)
        if(actual == 0){
            actual = 6
            return dayOfWeek.get(actual)
        }
        return dayOfWeek.get(actual - 1)
    }

    fun nextDay(day: String):String{
        var actual: Int = dayOfWeek.indexOf(day)
        if(actual == 6){
            actual = 0
            return dayOfWeek.get(actual)
        }
        return dayOfWeek.get(actual + 1)
    }
}