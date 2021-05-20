package net.codefastly.yumekai.fragments.Recent

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentRecentBinding
import net.codefastly.yumekai.helpers.RecyclesViews.MorePopularAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.RecentAdapter
import net.codefastly.yumekai.models.recents.ModelDTO
import net.codefastly.yumekai.models.recents.RecentsResponse
import net.codefastly.yumekai.models.recents.Result


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

        viewmodel = ViewModelProvider(this).get(RecentViewModel::class.java)

        inicializePopularRecyclerView()
        inicializeRecyclerView()
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
        viewmodel.getRecentsData().observe(viewLifecycleOwner, Observer { recents ->
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
        with(viewmodel) {

            getRecentsData().observeForever(Observer {
                history = it
                actualizeData()
            })

            getRecentsTVData().observeForever(Observer {
                tv = it
                actualizeData()
            })
            getRecentsMoviesData().observeForever(Observer {
                movies = it
                actualizeData()
            })

            getRecentsOvaData().observeForever(Observer {
                ova = it
                actualizeData()
            })

            getRecentsONAData().observeForever(Observer {
                ona = it
                actualizeData()
            })

            getRecentsSpecialData().observeForever(Observer {
                special = it
                actualizeData()
            })
        }
    }

    fun actualizeData() {

        var list = listOf<ModelDTO>(
            ModelDTO(
                1,
                "Historial",
                "Last seen anime",
                R.drawable.ic_baseline_navigate_next_24,
                R.color.red_primary,
                "More Historial",
                viewmodel.history
            ),
            ModelDTO(
                2,
                "TV",
                "Recent TV",
                R.drawable.ic_outline_article_36,
                R.color.red_primary,
                "More TV",
                viewmodel.tv
            ),

            ModelDTO(
                3,
                "Peliculas",
                "Recent Movies",
                R.drawable.ic_outline_article_36,
                R.color.red_primary,
                "More Peliculas",
                viewmodel.movies
            ),

            ModelDTO(
                4,
                "OVA",
                "Recent OVA's",
                R.drawable.ic_outline_article_36,
                R.color.red_primary,
                "More OVA",
                viewmodel.ova
            ),
            ModelDTO(
                5,
                "ONA",
                "Recent ONA's",
                R.drawable.ic_outline_article_36,
                R.color.red_primary,
                "More ONA",
                viewmodel.ona
            ),
            ModelDTO(
                6,
                "Special",
                "Recent Special's",
                R.drawable.ic_outline_article_36,
                R.color.red_primary,
                "More Special",
                viewmodel.special
            ),
        )
        Log.d("Hola", list.toString())
        adapter.setData(list)
        adapter.notifyDataSetChanged()
        observePopularData()
    }
}