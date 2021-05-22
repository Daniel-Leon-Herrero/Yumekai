package net.codefastly.yumekai.fragments.Shop.Series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentSearchBinding
import net.codefastly.yumekai.databinding.FragmentSeriesBinding
import net.codefastly.yumekai.helpers.RecyclesViews.VolumesMangaAdapter


class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    private lateinit var volumesAdapter: VolumesMangaAdapter

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(SeriesViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_series, container, false)

        initRecyclerView()
        viewModel.getAllVolumes("manga")

        viewModel.volumes.observe(requireActivity(), { dataList ->
            volumesAdapter.setData( dataList )
            volumesAdapter.notifyDataSetChanged()
        })


        return binding.root
    }

    private fun initRecyclerView(){
        volumesAdapter = VolumesMangaAdapter( requireContext() )
        with(binding.seriesScreenVolumesRv){
            layoutManager = GridLayoutManager( requireContext(), 2, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = volumesAdapter
        }
    }


}