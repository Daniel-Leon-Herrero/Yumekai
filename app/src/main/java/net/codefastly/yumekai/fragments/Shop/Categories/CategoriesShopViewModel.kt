package net.codefastly.yumekai.fragments.Shop.Categories

import android.graphics.drawable.Drawable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.shop.CategoriesShop
import net.codefastly.yumekai.repository.online.RepositoryFirebase

class CategoriesShopViewModel: ViewModel() {

    var bannerData = listOf<Int>()

    private lateinit var _owner: LifecycleOwner

    private val _categories = MutableLiveData<MutableList<CategoriesShop>>()
    val categories : LiveData<MutableList<CategoriesShop>> get() = _categories

    private var repositoryFirebase : RepositoryFirebase = RepositoryFirebase()

    init {
        bannerData = listOf(R.drawable.yumekai_shop_banner)
    }

    fun attach( fragment: CategoriesShopFragment ){
        _owner = fragment
    }

    fun fetchShopCategories(){
        this.repositoryFirebase.getAllShopCategories().observe( _owner, { categorieList ->
            this._categories.value = categorieList
        })
    }


}