package net.codefastly.yumekai.fragments.Calendar

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class CalendarViewModel : ViewModel() {

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

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jikan.moe/v3/schedule/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun searchByDay(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val call = getRetrofit().create(APIService::class.java)
                    .getCalendarAnimes("${obtainDayOfWeek(context)}")
                val datos = call.body()
                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        val images: MutableList<String> = arrayListOf()
                        datos?.day?.forEach { day ->
                            images.add(day.image_url)
                        }
                        animeImageList.clear()
                        animeImageList.addAll(images)
                        Log.d("Sear","sear")
                    }
                }


                if (call.isSuccessful) datos!!.request_hash else ""
            }

        }
    }
}