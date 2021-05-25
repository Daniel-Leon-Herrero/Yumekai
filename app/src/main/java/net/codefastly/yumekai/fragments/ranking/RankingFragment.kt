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

class RankingFragment : Fragment() {

    private lateinit var viewmodel: RankingViewModel
    private lateinit var binding: FragmentRankingBinding
    private lateinit var _adapterTag: ItemTagAdapter

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
        inicialzeTagAdapter()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun inicialzeTagAdapter() {
        _adapterTag = ItemTagAdapter(requireContext())
        with(binding.rankingRecyclerViewTag) {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = _adapterTag
        }
        _adapterTag.setData(viewmodel.itemTag)
        _adapterTag.notifyDataSetChanged()

    }
}
