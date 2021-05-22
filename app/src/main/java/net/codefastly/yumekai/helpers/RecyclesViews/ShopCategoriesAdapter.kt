package net.codefastly.yumekai.helpers.RecyclesViews

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.Search.SearchFragment
import net.codefastly.yumekai.models.shop.CategoriesShop

class ShopCategoriesAdapter(val context: Context):RecyclerView.Adapter<ShopCategoriesAdapter.categoriesViewHolder>() {

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
                findViewById<ImageView>(R.id.item_shop_category_image).setImageResource(cat.image)
                findViewById<TextView>(R.id.item_shop_category_name).text = cat.name
                with(findViewById<ImageView>(R.id.item_shop_category_lock)){
                    if(cat.lock){
                        visibility = View.VISIBLE
                    }else{
                        visibility = View.GONE
                    }

                    itemView.setOnClickListener {
                        if(cat.lock){
                            Snackbar.make(itemView,"This category is not available now",Snackbar.LENGTH_SHORT).show()
                        }else{
                          /*  val mContext = context as FragmentActivity
                            val transaction = mContext.supportFragmentManager.beginTransaction()
                            transaction.replace(
                                R.id.nav_host_fullscreen_fragment,
                                SearchFragment(),
                                "searchFragment"
                            )
                            transaction.commit()*/
                            Snackbar.make(itemView,"Go to "+ cat.name,Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }


            }
        }
    }
}