package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.shop.CategoriesShop

class ShopBannerAdapter(val context: Context): RecyclerView.Adapter<ShopBannerAdapter.ShopBannerViewHolder>() {

    private var dataList = listOf<Int>()

    fun setData(data: List<Int>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopBannerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_shop_category_banner, parent, false)
        return ShopBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopBannerViewHolder, position: Int) {
        val banner = dataList[position]
        holder.bind(banner)
    }

    override fun getItemCount(): Int {
        if(dataList.size > 0){
            return dataList.size
        }else {
            return 0
        }
    }

    inner class ShopBannerViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        fun bind(banner: Int){
            itemView.findViewById<ImageView>(R.id.item_shop_banner_image).setImageResource(banner)
        }
    }
}