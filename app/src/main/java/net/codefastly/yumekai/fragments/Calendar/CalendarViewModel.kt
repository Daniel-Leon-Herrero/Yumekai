package net.codefastly.yumekai.fragments.Calendar

import android.content.Context
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.R
import java.util.*

class CalendarViewModel: ViewModel() {



    fun obtainDayOfWeek(context: Context): String {
        var dayString: String = ""
        var calendar: Calendar = Calendar.getInstance()
        var dayInt: Int = calendar.get(Calendar.DAY_OF_WEEK)

        when(dayInt){
            Calendar.SUNDAY -> dayString = context.getString(R.string.Eliminar)
            Calendar.MONDAY -> dayString = context.getString(R.string.Monday)
            Calendar.TUESDAY -> dayString = context.getString(R.string.Tuesday)
            Calendar.WEDNESDAY -> dayString = context.getString(R.string.Wednesday)
            Calendar.THURSDAY -> dayString = context.getString(R.string.Thursday)
            Calendar.FRIDAY -> dayString = context.getString(R.string.Feiday)
            Calendar.SATURDAY -> dayString = context.getString(R.string.Saturday)
        }
        return dayString
    }
}