package net.codefastly.yumekai.fragments.Shop.Categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentShopCategoriesBinding
import net.codefastly.yumekai.helpers.RecyclesViews.ShopBannerAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.ShopCategoriesAdapter

class CategoriesShopFragment : Fragment() {

    private lateinit var binding : FragmentShopCategoriesBinding
    private lateinit var viewModel: CategoriesShopViewModel
    private lateinit var adapterCategories: ShopCategoriesAdapter
    private lateinit var adapterBanner: ShopBannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shop_categories,container,false)
        viewModel = ViewModelProvider(this).get(CategoriesShopViewModel::class.java)
        binding.shopCategoryBtnBack.setOnClickListener {
            requireActivity().finish()
        }

        inicializeBanner()
        inicializeCategories()

        initViewModel()

        viewModel.categories.observe(viewLifecycleOwner, { categoriesList ->
            adapterCategories.setData( categoriesList )
            adapterCategories.notifyDataSetChanged()
            binding.shopCategoryScreenLoading.visibility = View.GONE
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initViewModel(){
        viewModel.attach( this )
        viewModel.fetchShopCategories()
    }

    fun inicializeBanner(){
        adapterBanner = ShopBannerAdapter(requireContext())
        with(binding.shopCategoryRVBanner){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = adapterBanner
        }
        adapterBanner.setData(viewModel.bannerData)
        adapterBanner.notifyDataSetChanged()
    }

    fun inicializeCategories(){
        adapterCategories = ShopCategoriesAdapter(requireContext(), this)
        with(binding.shopCategoryRVCategories){
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = adapterCategories
        }
    }
}