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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentRecentBinding
import net.codefastly.yumekai.helpers.RecyclesViews.MorePopularAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.RecentAdapter
import net.codefastly.yumekai.models.recents.ModelDTO
import net.codefastly.yumekai.models.recents.RecentsResponse
import net.codefastly.yumekai.models.recents.Result
import net.codefastly.yumekai.viewmodels.getRecentsViewModel


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