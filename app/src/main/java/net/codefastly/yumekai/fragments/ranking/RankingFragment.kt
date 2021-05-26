package net.codefastly.yumekai.fragments.ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentRankingBinding
import net.codefastly.yumekai.helpers.RecyclesViews.ItemTagAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.TopAnimeAdapter

class RankingFragment : Fragment() {

    private lateinit var viewmodel: RankingViewModel
    private lateinit var binding: FragmentRankingBinding
    private lateinit var _adapterTag: ItemTagAdapter
    private lateinit var _topAdapter: TopAnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProvider(this).get(RankingViewModel::class.java)
        viewmodel.fetchData(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ranking, container, false)

        binding.rankingScreenBtnBack.setOnClickListener {
            requireActivity().finish()
        }
        inicializeTopAdapter()
        inicialzeTagAdapter()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun inicialzeTagAdapter() {
        _adapterTag = ItemTagAdapter(requireContext(),_topAdapter,viewmodel,this)
        with(binding.rankingRecyclerViewTag) {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = _adapterTag
        }
        _adapterTag.setData(viewmodel.itemTag)
        _adapterTag.notifyDataSetChanged()

    }

    private fun inicializeTopAdapter(){
        _topAdapter = TopAnimeAdapter(requireContext())
        with(binding.rankingRecyclerViewRanking){
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = _topAdapter
        }
        viewmodel.getData(0).observe(viewLifecycleOwner, Observer {
            _topAdapter.setData(it.top)
            _topAdapter.notifyDataSetChanged()
        })
    }
}
