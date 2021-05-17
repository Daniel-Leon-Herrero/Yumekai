package net.codefastly.yumekai.fragments.AnimeDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentAnimeDetailsBinding
import net.codefastly.yumekai.fragments.AnimeDetails.AnimeDetailsFragmentArgs
import net.codefastly.yumekai.helpers.RecyclesViews.CategoryAnimeAdapter
import net.codefastly.yumekai.models.calendar.Genre

class AnimeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentAnimeDetailsBinding
    private lateinit var viewModel: AnimeDetailsViewModel
    private lateinit var recyclerViewCategories: RecyclerView
    private lateinit var adapterCategories: CategoryAnimeAdapter
    val args: AnimeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AnimeDetailsViewModel::class.java)

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_anime_details, container, false)

        viewModel.anime.value = args.anime1

        bindData()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun bindData() {
        with(binding) {
            // Recuperar datos anteriores
            with(viewModel.anime.value?.day) {
                animeBtnBack.setOnClickListener {
                    requireActivity().findNavController(R.id.nav_host_fragment).navigateUp()
                }
                if (this?.image_url!!.isNotEmpty()) {
                    Picasso.get().load(this?.image_url).into(animeDetailsImg)
                }
                animeDetailsTitle.text = this?.title
                animeDetailsSynopsis.text = this?.synopsis
                animeDetailsType.text = this?.type
                inicializeCategoryAdapter(this?.genres)

                //Mas Detalles
                with(viewModel.animeDetails) {
                    observe(viewLifecycleOwner, Observer {
                        with(this?.value) {
                            animeDetailsStatus.text = this?.status
                            animeDetailsDuration.text = this?.duration
                            animeDetailsPosition.text = "#" + this?.rank.toString()
                            animeDetailsEnglishTitle.text = this?.title_english
                            animeDetailsJapaneseTitle.text = this?.title_japanese
                            animeDetailsAiring.text = this?.aired?.string


                        }
                    })
                }
            }
        }
    }

    private fun inicializeCategoryAdapter(genre: List<Genre>) {
        recyclerViewCategories = binding.animeDetailsRVCategories
        adapterCategories = CategoryAnimeAdapter(requireContext())
        recyclerViewCategories.adapter = adapterCategories
        observeData(genre)
    }

    private fun observeData(genre: List<Genre>) {
        adapterCategories.setListCategories(genre)
        adapterCategories.notifyDataSetChanged()
    }

}