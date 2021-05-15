package net.codefastly.yumekai.fragments.Anime

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentAnimeBinding


class AnimeFragment : Fragment() {
    private lateinit var binding: FragmentAnimeBinding
    private lateinit var viewModel: AnimeViewModel
    val args: AnimeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(AnimeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anime, container, false)

        binding.animeBtnBack.setOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment).navigateUp()
        }
        viewModel.anime = args.anime1
        Log.d("Anime", viewModel.anime.toString())

        // Inflate the layout for this fragment
        return binding.root
    }
}