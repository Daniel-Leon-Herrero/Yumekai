package net.codefastly.yumekai.fragments.Shop.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentProductDetailsBinding
import net.codefastly.yumekai.models.shop.VolumeShop

class ProductDetailsFragment( val volumeItem: VolumeShop ) : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false)

        this.addDetailsInfo()

        binding.productDetailsScreenBtnClose.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root

    }

    private fun addDetailsInfo(){

        with(binding){

            Picasso.get().load(volumeItem.image_url).into( productDetailsScreenProductImg )

            productDetailsScreenTitle.text = "Volume ${volumeItem.volume}"
            productDetailsScreenStock.text = "Available"

            productDetailsScreenProductTitle.text = volumeItem.title
            productDetailsScreenProductPriceRtl.text = "$${volumeItem.price_rtl}"
            productDetailsScreenProductPrice.text = "$${volumeItem.price}"

            productDetailsScreenStartDescription.text = volumeItem.start_description
            productDetailsScreenDescription.text = volumeItem.description

            if( volumeItem.details !== null){
                productDetailsScreenDetailsPublisher.text = volumeItem.details.publisher
                productDetailsScreenDetailsMedia.text = volumeItem.details.media
                var sb: String = ""
                volumeItem.details.genre.forEachIndexed { index, genre ->
                    sb += if( index === volumeItem.details.genre.lastIndex){
                        genre
                    }else{
                        "${genre}, "
                    }
                }
                productDetailsScreenDetailsGenre.text = if( sb.isNotEmpty() ) sb else "-"
                sb = ""
                volumeItem.details.themes.forEachIndexed { index, theme ->
                    sb += if( index === volumeItem.details.genre.lastIndex){
                        theme
                    }else{
                        "${theme}, "
                    }
                }
                productDetailsScreenDetailsThemes.text = if( sb.isNotEmpty() ) sb else "-"
                productDetailsScreenDetailsReleaseDate.text = volumeItem.details.release_date
                productDetailsScreenDetailsAgeRating.text = volumeItem.details.age_rating
                productDetailsScreenDetailsPageCount.text = volumeItem.details.page_count.toString()
                productDetailsScreenDetailsDimensionalWeight.text = volumeItem.details.dimensional_weight.toString()
                productDetailsScreenDetailsLanguage.text = volumeItem.details.language

            }


        }

    }



}