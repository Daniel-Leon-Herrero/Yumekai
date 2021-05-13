package net.codefastly.yumekai.fragments.Calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentCalendarBinding
import net.codefastly.yumekai.helpers.RecyclesViews.CalendarAnimeAdapter

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private lateinit var viewModel: CalendarViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CalendarAnimeAdapter
    private lateinit var day: String;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        viewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)

        day = viewModel.obtainDayOfWeek(requireContext())
        inicializeAnimeAdapter()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun inicializeAnimeAdapter(){
        recyclerView = binding.calendarRVAnime
        adapter = CalendarAnimeAdapter(requireContext())
        recyclerView.adapter = adapter
        observeData()
    }

    fun observeData() {

        viewModel.searchByDay(day).observe(viewLifecycleOwner, Observer { animes ->
            adapter.setListAnimes(animes)
            adapter.notifyDataSetChanged()
        })

    }


    override fun onStart() {
        binding.calendarBtnDay.setText(viewModel.obtainDayOfWeek(requireContext()))
        super.onStart()
    }
}