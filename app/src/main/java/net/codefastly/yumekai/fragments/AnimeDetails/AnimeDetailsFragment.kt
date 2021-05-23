package net.codefastly.yumekai.fragments.AnimeDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentAnimeDetailsBinding
import net.codefastly.yumekai.helpers.RecyclesViews.CategoryAnimeAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.CharacterAnimeAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.StaffAnimeAdapter
import net.codefastly.yumekai.models.anime.Genre
import net.codefastly.yumekai.models.calendar.AnimeDTO
import java.io.Serializable

class AnimeDetailsFragment(val anime: Int, val previousFragment: Fragment?) : Fragment() {
    private lateinit var binding: FragmentAnimeDetailsBinding
    private lateinit var viewModel: AnimeDetailsViewModel
    private lateinit var recyclerViewCategories: RecyclerView
    private lateinit var adapterCategories: CategoryAnimeAdapter
    private lateinit var recyclerViewCharacter: RecyclerView
    private lateinit var adapterCharacter: CharacterAnimeAdapter
    private lateinit var recyclerViewStaff: RecyclerView
    private lateinit var adapterStaff: StaffAnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AnimeDetailsViewModel::class.java)

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_anime_details, container, false)

        binding.animeBtnBack.setOnClickListener {
            if(previousFragment == null){
                requireActivity().finish()
            }else{
                val mContext = context as FragmentActivity
                val transaction = mContext.supportFragmentManager.beginTransaction()
                transaction.remove(this).show(previousFragment)
                transaction.commit()
            }

        }
        viewModel.anime.value = anime

        bindData()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun bindData() {
        with(binding) {
            var sb = StringBuilder()

                //Mas Detalles
                with(viewModel.animeDetails) {
                    observe(viewLifecycleOwner, Observer {
                        with(this?.value) {
                            if (this?.image_url!!.isNotEmpty()) {
                                Picasso.get().load(this?.image_url).into(animeDetailsImg)
                            }
                            animeDetailsTitle.text = this?.title
                            animeDetailsSynopsis.text = this?.synopsis
                            animeDetailsType.text = this?.type
                            inicializeCategoryAdapter(this?.genres)
                            if (this?.licensors?.size < 1) {
                                sb.append("-")
                            } else {
                                this?.licensors.forEach { licensors ->
                                    sb.append(animeDetailsLicenses.text).append(licensors.name)
                                    if (this?.licensors?.size > 1) {
                                        sb.append(", ")
                                    }
                                }
                            }
                            animeDetailsLicenses.text = sb.toString()
                            animeDetailsSource.text = this?.source
                            animeDetailsMembres.text = this?.members.toString()
                            animeDetailsStatus.text = this?.status
                            animeDetailsDuration.text = this?.duration
                            animeDetailsPosition.text = "#" + this?.rank.toString()
                            animeDetailsEnglishTitle.text = this?.title_english
                            animeDetailsJapaneseTitle.text = this?.title_japanese
                            animeDetailsAiring.text = this?.aired?.string
                            animeDetailsReleased.text = this?.premiered
                            sb.clear()
                            this?.producers?.forEach { producer ->
                                sb.append(animeDetailsProducers.text).append(producer.name)
                                    .append(", ")
                            }
                            animeDetailsProducers.text = sb.toString()

                            sb.clear()
                            this?.studios?.forEach { studio ->
                                sb.append(animeDetailsStudios.text).append(studio.name)
                                if (this?.studios?.size > 1) {
                                    sb.append(", ")
                                }
                            }
                            animeDetailsStudios.text = sb.toString()
                            animeDetailsPuntuation.text =
                                "${this?.score} ( Puntuated by ${this?.scored_by} users )"
                            animeDetailsPopularity.text = this?.popularity.toString()
                            animeDetailsFavourites.text = this?.favorites.toString()

                            //Falta por encontrar
                            animeDetailsClasification.text = "-"
                        }
                    })
                }

                with(viewModel.animeCharacter.value) {
                    inicializeCharacterStaffAdapter()
                }

        }
    }

    private fun inicializeCategoryAdapter(genre: List<Genre>) {
        recyclerViewCategories = binding.animeDetailsRVCategories
        adapterCategories = CategoryAnimeAdapter(requireContext())
        recyclerViewCategories.adapter = adapterCategories
        observeData(genre)
    }

    private fun inicializeCharacterStaffAdapter() {
        recyclerViewCharacter = binding.animeDetailsRVCharacter
        adapterCharacter = CharacterAnimeAdapter(requireContext())
        recyclerViewCharacter.adapter = adapterCharacter


        recyclerViewStaff = binding.animeDetailsRVStaff
        adapterStaff = StaffAnimeAdapter(requireContext())
        recyclerViewStaff.adapter = adapterStaff

        observeCharacterStaffData()


    }


    private fun observeData(genre: List<Genre>) {
        adapterCategories.setListCategories(genre)
        adapterCategories.notifyDataSetChanged()
    }

    private fun observeCharacterStaffData() {
        with(viewModel.animeCharacter) {
            observe(viewLifecycleOwner, Observer {
                adapterCharacter.setListCharacter(it.characters)
                adapterCharacter.notifyDataSetChanged()

                adapterStaff.setDataStaff(it.staff)
                adapterStaff.notifyDataSetChanged()
            })
        }
    }

}