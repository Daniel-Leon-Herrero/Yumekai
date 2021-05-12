package net.codefastly.yumekai.fragments.Calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentCalendarBinding
import java.time.LocalDate
import java.util.*

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onStart() {
        binding.calenderLblDay.setText(obtainDayOfWeek())
        super.onStart()
    }

    fun obtainDayOfWeek(): String {
        var dayString: String = ""
        var calendar: Calendar = Calendar.getInstance()
        var dayInt: Int = calendar.get(Calendar.DAY_OF_WEEK)

        when(dayInt){
            Calendar.SUNDAY -> dayString = getString(R.string.Sunday)
            Calendar.MONDAY -> dayString = getString(R.string.Monday)
            Calendar.TUESDAY -> dayString = getString(R.string.Tuesday)
            Calendar.WEDNESDAY -> dayString = getString(R.string.Wednesday)
            Calendar.THURSDAY -> dayString = getString(R.string.Thursday)
            Calendar.FRIDAY -> dayString = getString(R.string.Feiday)
            Calendar.SATURDAY -> dayString = getString(R.string.Saturday)
        }
        return dayString
    }
}