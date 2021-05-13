package net.codefastly.yumekai.fragments.Calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentCalendarBinding
import net.codefastly.yumekai.helpers.RecyclesViews.CalenderAnimeAdapter

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private lateinit var viewModel: CalendarViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CalenderAnimeAdapter
    lateinit var recyclerViewNuevo: RecyclerView



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        viewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
        initRecycleView()
        getData()
        binding.calenderBtnPrevius.setOnClickListener {
          //  viewModel.searchByDay(requireContext())
        }

        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onStart() {
        binding.calendarBtnDay.setText(viewModel.obtainDayOfWeek(requireContext()))
        super.onStart()
    }

    private fun initRecycleView(){
        recyclerView = binding.calendarRvAnimes
        adapter = CalenderAnimeAdapter(requireContext())
        recyclerView.adapter = adapter
        getData()
    }

    private fun getData(){
        Log.d("GetData","GetData")
        viewModel.searchByDay(requireContext())
        adapter.setListImagesAnimes(viewModel.animeImageList)
        adapter.notifyDataSetChanged()
    }


}