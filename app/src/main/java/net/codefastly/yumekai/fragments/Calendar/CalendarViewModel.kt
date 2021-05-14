package net.codefastly.yumekai.fragments.Calendar

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.R
import net.codefastly.yumekai.repository.repositoryAPI
import java.util.*

class CalendarViewModel : ViewModel() {
    private val repo = repositoryAPI()
    var day: MutableLiveData<String> = MutableLiveData("")
    val dayOfWeek = arrayOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")


    fun obtainDayOfWeek(context: Context) {
        var dayString: String = ""
        var calendar: Calendar = Calendar.getInstance()
        var dayInt: Int = calendar.get(Calendar.DAY_OF_WEEK)

        when (dayInt) {
            Calendar.SUNDAY -> dayString = context.getString(R.string.Sunday)
            Calendar.MONDAY -> dayString = context.getString(R.string.Monday)
            Calendar.TUESDAY -> dayString = context.getString(R.string.Tuesday)
            Calendar.WEDNESDAY -> dayString = context.getString(R.string.Wednesday)
            Calendar.THURSDAY -> dayString = context.getString(R.string.Thursday)
            Calendar.FRIDAY -> dayString = context.getString(R.string.Feiday)
            Calendar.SATURDAY -> dayString = context.getString(R.string.Saturday)
        }
        day.value = dayString
    }



    fun searchByDay(day: String): LiveData<MutableList<String>> {
        val mutableData = MutableLiveData<MutableList<String>>()
        when(day){
            "monday" -> repo.getCalenderAnimeMonday(day).observeForever { animes ->
                            mutableData.value = animes
                            }
            "tuesday" -> repo.getCalenderAnimeTuesday(day).observeForever { animes ->
                            mutableData.value = animes
                            }
            "wednesday" -> repo.getCalenderAnimeWednesday(day).observeForever { animes ->
                            mutableData.value = animes
                            }

            "thursday" -> repo.getCalenderAnimeThursday(day).observeForever { animes ->
                            mutableData.value = animes
                             }

            "friday" -> repo.getCalenderAnimeFriday(day).observeForever { animes ->
                            mutableData.value = animes
                            }

            "saturday" -> repo.getCalenderAnimeSaturday(day).observeForever { animes ->
                            mutableData.value = animes
                            }

            "sunday" -> repo.getCalenderAnimeSunday(day).observeForever { animes ->
                            mutableData.value = animes
                            }
        }
        return mutableData
    }

    fun previusDay(day: String):String{
        var actual: Int = dayOfWeek.indexOf(day)
        if(actual == 0){
            actual = 7
        }
        return dayOfWeek.get(actual - 1)
    }

    fun nextDay(day: String):String{
        var actual: Int = dayOfWeek.indexOf(day)
        if(actual == 6){
            actual = 0
        }
        return dayOfWeek.get(actual + 1)
    }
}