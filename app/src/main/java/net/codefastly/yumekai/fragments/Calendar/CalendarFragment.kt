package net.codefastly.yumekai.fragments.Calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private lateinit var viewModel: CalendarViewModel
    private lateinit var adapter:
    private var url: String = "https://api.jikan.moe/v3/schedule/tuesday"


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        viewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)

        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onStart() {
        binding.calenderLblDay.setText(viewModel.obtainDayOfWeek(requireContext()))
        super.onStart()
    }

    //https://cursokotlin.com/capitulo-20-consumiento-apis-retrofit-2/ 6.44
}