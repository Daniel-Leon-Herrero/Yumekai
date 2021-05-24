package net.codefastly.yumekai.fragments.History

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentHistoryBinding
import net.codefastly.yumekai.helpers.RecyclesViews.HistoryAnimeAdapter


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewmodel: HistoryViewModel
    private lateinit var _adapter: HistoryAnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        viewmodel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        viewmodel.setFetch(requireContext(), this)
        initRecyclerView()

        binding.historyScreenBtnBack.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root
    }

    private fun initRecyclerView() {
        with(binding.historyScreenRv) {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            _adapter = HistoryAnimeAdapter(requireContext(), this@HistoryFragment)
            this.adapter = _adapter
            observeData()
        }

    }

    private fun observeData() {
        viewmodel.historyItems.observe(viewLifecycleOwner, Observer {
            _adapter.setData(it)
            _adapter.notifyDataSetChanged()
        })
    }


}