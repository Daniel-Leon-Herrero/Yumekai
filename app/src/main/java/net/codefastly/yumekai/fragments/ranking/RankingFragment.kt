package net.codefastly.yumekai.fragments.ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentRankingBinding

class RankingFragment : Fragment() {

    private lateinit var viewmodel: RankingViewModel
    private lateinit var binding: FragmentRankingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProvider(this).get(RankingViewModel::class.java)
        binding = DataBindingUtil.inflate( inflater ,R.layout.fragment_ranking, container, false )

        binding.rankingScreenBtnBack.setOnClickListener {
            requireActivity().finish()
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}