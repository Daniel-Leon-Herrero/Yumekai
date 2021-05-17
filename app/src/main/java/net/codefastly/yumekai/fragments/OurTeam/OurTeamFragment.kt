package net.codefastly.yumekai.fragments.OurTeam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentOurTeamBinding


class OurTeamFragment : Fragment() {

    private lateinit var binding : FragmentOurTeamBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_our_team , container, false  )







        return binding.root
    }

}