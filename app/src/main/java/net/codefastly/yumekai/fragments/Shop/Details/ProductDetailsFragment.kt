package net.codefastly.yumekai.fragments.Shop.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentProductDetailsBinding
import net.codefastly.yumekai.models.shop.VolumeShop

class ProductDetailsFragment( val volume: VolumeShop ) : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false)





        return binding.root

    }

    private fun addDetailsInfo(){

    }



}