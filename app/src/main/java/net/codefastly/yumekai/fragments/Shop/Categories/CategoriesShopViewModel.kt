package net.codefastly.yumekai.fragments.Shop.Categories

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.shop.CategoriesShop

class CategoriesShopViewModel: ViewModel() {

    var bannerData = listOf<Int>()
    var categoriesItem = listOf<CategoriesShop>()

    init {
        bannerData = listOf(R.drawable.yumekai_shop_banner)
        categoriesItem = listOf(CategoriesShop("Manga","book",true, 1),
            CategoriesShop("Figures", "toy",false, 2))
    }


}