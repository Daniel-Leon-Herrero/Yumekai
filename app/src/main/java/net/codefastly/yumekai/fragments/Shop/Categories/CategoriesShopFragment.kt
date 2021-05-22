package net.codefastly.yumekai.fragments.Shop.Categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentShopCategoriesBinding
import net.codefastly.yumekai.helpers.RecyclesViews.ShopCategoriesAdapter

class CategoriesShopFragment : Fragment() {

    private lateinit var binding : FragmentShopCategoriesBinding
    private lateinit var viewModel: CategoriesShopViewModel
    private lateinit var adapter: ShopCategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shop_categories,container,false)
        viewModel = ViewModelProvider(this).get(CategoriesShopViewModel::class.java)
        binding.shopCategoryBtnBack.setOnClickListener {
            requireActivity().finish()
        }
        inicializeCategories()

        // Inflate the layout for this fragment
        return binding.root
    }

    fun inicializeCategories(){
        adapter = ShopCategoriesAdapter(requireContext())
        binding.shopCategoryRVCategories.adapter = adapter
        adapter.setData(viewModel.categoriesItem)
        adapter.notifyDataSetChanged()

    }
}