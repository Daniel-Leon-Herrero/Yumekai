package net.codefastly.yumekai.fragments.Recent

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.DashboardFullScreen.DashboardFullScreen
import net.codefastly.yumekai.databinding.FragmentRecentBinding
import net.codefastly.yumekai.fragments.AnimeDetails.AnimeDetailsFragment
import net.codefastly.yumekai.helpers.RecyclesViews.MorePopularAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.RecentAdapter
import net.codefastly.yumekai.utilities.getRecentsViewModel


class RecentFragment : Fragment() {

    private lateinit var binding: FragmentRecentBinding
    private lateinit var viewmodel: RecentViewModel
    private lateinit var recyclerViewPopular: RecyclerView
    private lateinit var adapterPopular: MorePopularAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recent, container, false)

        viewmodel = getRecentsViewModel()
        viewmodel.setContext(requireContext(),this)
        inicializePopularRecyclerView()
        inicializeRecyclerView()

        binding.recentScreenBtnRandom.setOnClickListener {
            val randomAnime: Int = (1..499).random()
            val intent = Intent( context, DashboardFullScreen::class.java ).apply {
                this.putExtra("FULL_SCREEN_TO_LOAD", 1122 )
                this.putExtra("ANIME_DETAILS", randomAnime)
            }
            startActivity( intent )
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    fun inicializePopularRecyclerView() {
        recyclerViewPopular = binding.recentPopularRecyclerView
        adapterPopular = MorePopularAdapter(requireContext())
        recyclerViewPopular.adapter = adapterPopular
        observePopularData()


    }

    fun observePopularData() {
        viewmodel.recentsData.observe(viewLifecycleOwner, Observer { recents ->
            adapterPopular.setData(recents.results)
            adapterPopular.notifyDataSetChanged()
        })
    }

    fun inicializeRecyclerView() {
        recyclerView = binding.recentRecyclerView
        adapter = RecentAdapter(requireContext())
        recyclerView.adapter = adapter
        ObserveData()

    }

    fun ObserveData() {
        viewmodel.list.observe(viewLifecycleOwner, Observer {
            refresdata()
        })

    }


    fun refresdata() {
        adapter.setData(viewmodel.list.value!!)
        adapter.notifyDataSetChanged()
        observePopularData()
    }
}