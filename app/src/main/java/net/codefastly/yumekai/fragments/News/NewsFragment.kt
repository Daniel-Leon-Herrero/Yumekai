package net.codefastly.yumekai.fragments.News

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentNewsBinding
import net.codefastly.yumekai.helpers.RecyclesViews.NewsAnimeAdapter
import net.codefastly.yumekai.viewmodels.getNewsViewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var  viewModel: NewsViewModel

    private lateinit var recyclerAdapter: NewsAnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_news, container, false )
        viewModel = getNewsViewModel()
        this.initRecyclerView()

        if(!viewModel.fetchingData){
            viewModel.fetchFeedRSS()
        }

        binding.newsSwiperRefresh.setOnRefreshListener( SwipeRefreshLayout.OnRefreshListener {
            if( !viewModel.fetchingData ){
                viewModel.fetchFeedRSS()
                binding.newsSwiperRefresh.isRefreshing = false
            }
        })


        viewModel.articles.observe( viewLifecycleOwner, { articles ->
            recyclerAdapter.setArticles( articles )
            recyclerAdapter.notifyDataSetChanged()
        })


        return binding.root
    }

    private fun initRecyclerView(){
        recyclerAdapter = NewsAnimeAdapter(requireContext())
        with(binding.rvNews){
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recyclerAdapter
        }
    }

}