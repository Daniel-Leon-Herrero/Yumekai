package net.codefastly.yumekai.helpers.RecyclesViews

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.Search.SearchFragment
import net.codefastly.yumekai.fragments.Shop.Categories.CategoriesShopFragment
import net.codefastly.yumekai.fragments.Shop.Series.SeriesFragment
import net.codefastly.yumekai.models.shop.CategoriesShop

class ShopCategoriesAdapter(val context: Context, private val currentFragment: CategoriesShopFragment ):RecyclerView.Adapter<ShopCategoriesAdapter.categoriesViewHolder>() {

    private var dataList = listOf<CategoriesShop>()

    fun setData(data: List<CategoriesShop>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoriesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_shop_category_item, parent, false)
        return categoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: categoriesViewHolder, position: Int) {
        val anime = dataList[position]
        holder.bind(anime)
    }

    override fun getItemCount(): Int {
        if(dataList.size > 0){
            return dataList.size
        }else {
            return 0
        }
    }

    inner class categoriesViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        fun bind(cat: CategoriesShop){
            with(itemView){
                findViewById<ImageView>(R.id.item_shop_category_image).setImageResource( getImageResourceFromString( cat.icon ) )
                findViewById<TextView>(R.id.item_shop_category_name).text = cat.title
                with(findViewById<CardView>(R.id.item_shop_category_lock)){

                    visibility = if(!cat.available) View.VISIBLE else View.GONE

                    itemView.setOnClickListener {
                        if(!cat.available){
                            Snackbar.make(itemView,"This category is not available now",Snackbar.LENGTH_SHORT).show()
                        }else{
                            chargeOtherFragment( cat.title.toLowerCase() )
                        }
                    }
                }


            }
        }
    }

    private fun getImageResourceFromString( iconString: String ): Int {
        return when( iconString ) {
            "book" -> R.drawable.ic_baseline_menu_book_36
            "toys" -> R.drawable.ic_baseline_toys_36
            "audio" -> R.drawable.ic_outline_music_note_36
            "video" -> R.drawable.ic_outline_video_library_36
            "clothes" -> R.drawable.ic_baseline_shopping_bag_36
            "game" -> R.drawable.ic_baseline_sports_esports_36
            else -> R.drawable.ic_baseline_category_36
        }
    }

    private fun chargeOtherFragment( routeLink: String ){
        when( routeLink ){
            "manga" -> {
                val mContext = context as FragmentActivity
                val transaction = mContext.supportFragmentManager.beginTransaction()
                transaction.hide( currentFragment ).add(R.id.nav_host_fullscreen_fragment, SeriesFragment( currentFragment ), "seriesFragment")
                transaction.commit()
            }
        }
    }
}