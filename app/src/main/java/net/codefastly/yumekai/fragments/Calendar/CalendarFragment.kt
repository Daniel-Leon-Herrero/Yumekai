package net.codefastly.yumekai.fragments.Calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentCalendarBinding
import net.codefastly.yumekai.helpers.RecyclesViews.CalendarAnimeAdapter
import net.codefastly.yumekai.utilities.getCalendarViewModel

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private lateinit var viewModel: CalendarViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CalendarAnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)

        viewModel = getCalendarViewModel()
       // viewModel.obtainDayOfWeek(requireContext())
        viewModel.day.observe(viewLifecycleOwner, Observer {
            // Añadir Delay para no sobrecargar de peticiones
            observeData()
            actualizarDia()
        })

        binding.calenderBtnPrevius.setOnClickListener {
            viewModel.day.value = viewModel.previusDay(viewModel.day.value!!)
        }

        binding.calendarBtnNext.setOnClickListener {
            viewModel.day.value = viewModel.nextDay(viewModel.day.value!!)
        }
        inicializeAnimeAdapter()

     /*   binding.calendarSwipe.setOnRefreshListener {
            observeData()
            binding.calendarSwipe.isRefreshing = false
        }*/
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun inicializeAnimeAdapter(){
        recyclerView = binding.calendarRVAnime
        with( recyclerView ){
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            layoutManager = GridLayoutManager(requireContext(),2 , GridLayoutManager.VERTICAL, false)
        }
        adapter = CalendarAnimeAdapter(requireContext())
        recyclerView.adapter = adapter
        observeData()
    }

    fun observeData() {

        viewModel.anime.observe(viewLifecycleOwner, Observer { animes ->
            adapter.setListAnimes(animes)
            adapter.notifyDataSetChanged()
        })

    }


    fun actualizarDia(){
        binding.calendarBtnDay.setText(viewModel.day.value!!)
    }


    override fun onStart() {
        actualizarDia()
        super.onStart()
    }
}