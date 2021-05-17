package net.codefastly.yumekai.fragments.AnimeDetails

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
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentAnimeDetailsBinding
import net.codefastly.yumekai.fragments.AnimeDetails.AnimeDetailsFragmentArgs

class AnimeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentAnimeDetailsBinding
    private lateinit var viewModel: AnimeDetailsViewModel
    val args: AnimeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AnimeDetailsViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anime_details, container, false)

        viewModel.anime = args.anime1

        bindData()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun bindData() {
        with(binding) {
            with(viewModel){
                animeBtnBack.setOnClickListener {
                    requireActivity().findNavController(R.id.nav_host_fragment).navigateUp()
                }

                Picasso.get().load(anime.day.image_url).into(animeDetailsImg)

            }
        }
    }

}