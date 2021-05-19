package net.codefastly.yumekai.fragments.Recent

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentRecentBinding
import net.codefastly.yumekai.helpers.RecyclesViews.MorePopularAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.RecentAdapter
import net.codefastly.yumekai.models.recents.ModelDTO


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
        viewmodel.getRecentsData().observe(viewLifecycleOwner, Observer { recents ->
            adapterPopular.setData(recents.results)
            adapterPopular.notifyDataSetChanged()
        })

    }

    fun inicializeRecyclerView(){
        recyclerView = binding.recentRecyclerView
        adapter = RecentAdapter(requireContext())
        recyclerView.adapter = adapter
        var list = listOf<ModelDTO>(ModelDTO("Historial","Prueba",R.drawable.ic_baseline_navigate_next_24,R.color.red_primary,"More Historial"),
            ModelDTO("Peliculas","Prueba", R.drawable.ic_outline_article_36,R.color.red_primary, "More Peliculas")
        )
        adapter.setData(list)
        adapter.notifyDataSetChanged()
    }
}