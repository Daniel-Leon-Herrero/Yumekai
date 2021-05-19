package net.codefastly.yumekai.fragments.Recent

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
import net.codefastly.yumekai.helpers.RecyclesViews.RecentAdapter
import net.codefastly.yumekai.models.recents.ModelDTO


class RecentFragment : Fragment() {

    private lateinit var binding: FragmentRecentBinding
    private lateinit var viewmodel: RecentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recent, container, false)

        viewmodel = ViewModelProvider(this).get(RecentViewModel::class.java)

        inicializeRecyclerView()
        // Inflate the layout for this fragment
        return binding.root
    }

    fun inicializeRecyclerView(){
        recyclerView = binding.recentsRecyclerView
        adapter = RecentAdapter(requireContext())
        recyclerView.adapter = adapter
        viewmodel.getRecentsData().observe(viewLifecycleOwner, Observer { recents ->
            adapter.setData(recents.results)
            adapter.notifyDataSetChanged()
        })

    }

}