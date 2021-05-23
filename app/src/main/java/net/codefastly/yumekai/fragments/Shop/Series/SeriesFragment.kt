package net.codefastly.yumekai.fragments.Shop.Series

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentSeriesBinding
import net.codefastly.yumekai.helpers.RecyclesViews.SeriesMangaAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.VolumesMangaAdapter


class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    private lateinit var volumesAdapter: VolumesMangaAdapter
    private lateinit var seriesAdapter: SeriesMangaAdapter

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(SeriesViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_series, container, false)

        initRecyclersView()

        viewModel.getAllVolumes("manga")

        viewModel.series.observe( requireActivity(), { dataList ->
            seriesAdapter.setData( dataList )
            seriesAdapter.notifyDataSetChanged()
        })

        viewModel.volumes.observe(requireActivity(), { dataList ->
            volumesAdapter.setData( dataList )
            volumesAdapter.notifyDataSetChanged()
        })

        binding.seriesScreenBtnClose.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root
    }

    private fun initRecyclersView(){
        seriesAdapter = SeriesMangaAdapter( requireContext() )
        volumesAdapter = VolumesMangaAdapter( requireContext() )
        with(binding.seriesScreenVolumesRv){
            setHasFixedSize(true)
            layoutManager = GridLayoutManager( requireContext(), 2, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = volumesAdapter
        }
        with(binding.seriesScreenSeriesRv){
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager( requireContext(), LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = seriesAdapter
        }
    }


}