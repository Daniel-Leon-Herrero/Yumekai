package net.codefastly.yumekai.fragments.Calendar

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.R
import net.codefastly.yumekai.repository.repositoryAPI
import java.util.*

class CalendarViewModel : ViewModel() {
    private val repo = repositoryAPI()

    val animeImageList = mutableListOf<String>()


    fun obtainDayOfWeek(context: Context): String {
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
        return dayString
    }



    fun searchByDay(day: String): LiveData<MutableList<String>> {
        val mutableData = MutableLiveData<MutableList<String>>()
        repo.getCalenderAnime(day).observeForever{ animes ->
            mutableData.value = animes
        }
        return mutableData
    }
}