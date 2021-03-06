package net.codefastly.yumekai.fragments.Shop.Series

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentSeriesBinding
import net.codefastly.yumekai.fragments.Shop.Categories.CategoriesShopFragment
import net.codefastly.yumekai.helpers.RecyclesViews.SeriesMangaAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.VolumesMangaAdapter


class SeriesFragment( private val previousFragment: CategoriesShopFragment ) : Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    private lateinit var volumesAdapter: VolumesMangaAdapter
    private lateinit var seriesAdapter: SeriesMangaAdapter

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(SeriesViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_series, container, false)

        initViewModel()
        initRecyclersView()

        viewModel.series.observe(viewLifecycleOwner, { dataList ->
            seriesAdapter.setData( dataList )
            seriesAdapter.notifyDataSetChanged()
            viewModel.getVolumesBySerie( dataList[0].title )
        })

        viewModel.volumes.observe(viewLifecycleOwner, { dataList ->
            volumesAdapter.setData( dataList )
            volumesAdapter.notifyDataSetChanged()
        })

        viewModel.fetching.observe(viewLifecycleOwner, { visible ->
            with(binding.seriesScreenLoadingLayout){
                 visibility = if( visible ) View.VISIBLE else View.GONE
            }
        })

        binding.seriesScreenBtnClose.setOnClickListener {
            requireActivity().finish()
        }

        binding.seriesScreenBtnBack.setOnClickListener {
            if( previousFragment.isAdded ){
                val mContext = context as FragmentActivity
                val transaction = mContext.supportFragmentManager.beginTransaction()
                transaction.remove(this).show( previousFragment )
                transaction.commit()
            }
        }

        return binding.root
    }

    private fun initRecyclersView(){
        seriesAdapter = SeriesMangaAdapter( requireContext(), viewModel )
        volumesAdapter = VolumesMangaAdapter( requireContext(), this )
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

    private fun initViewModel(){
        viewModel.attach( this )
        viewModel.getAllAvailableSeries()
    }


}