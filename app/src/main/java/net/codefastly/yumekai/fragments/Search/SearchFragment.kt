package net.codefastly.yumekai.fragments.Search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentSearchBinding
import net.codefastly.yumekai.helpers.RecyclesViews.SearchAnimeAdapter


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by lazy { ViewModelProvider(this).get( SearchViewModel::class.java ) }

    private lateinit var searchAdapter: SearchAnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        initRecylcerView()

        binding.searchScreenSearchView.setOnQueryTextListener( object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if( !viewModel.fetchingData && !query.isNullOrEmpty()){
                    viewModel.fetchAnimeByQuery( query!! )
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return true
            }
        })

        viewModel.animeList.observe( viewLifecycleOwner , { animeList ->
            searchAdapter.setDataList( animeList )
            searchAdapter.notifyDataSetChanged()
        })

        binding.searchScreenBtnBack.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root
    }

    private fun initRecylcerView(){
        searchAdapter = SearchAnimeAdapter( requireContext() )
        with(binding.searchScreenRv){
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = searchAdapter
        }
    }


}