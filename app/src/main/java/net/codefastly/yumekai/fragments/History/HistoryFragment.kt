package net.codefastly.yumekai.fragments.History

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentHistoryBinding
import net.codefastly.yumekai.helpers.RecyclesViews.HistoryAnimeAdapter


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var adapter: HistoryAnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_history, container, false )

        initRecyclerView()

        binding.historyScreenBtnBack.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root
    }

    private fun initRecyclerView(){
        with( binding.historyScreenRv ){
            setHasFixedSize( true )
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager( requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = HistoryAnimeAdapter( requireContext() )
        }

    }


}