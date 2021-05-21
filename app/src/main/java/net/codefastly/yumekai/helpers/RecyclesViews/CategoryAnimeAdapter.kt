package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.anime.Genre

class CategoryAnimeAdapter(private val context: Context) :
    RecyclerView.Adapter<CategoryAnimeAdapter.AnimeCategoryViewHolder>() {

    private  var dataList = listOf<Genre>()

    fun setListCategories(data: List<Genre>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeCategoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_anime_category, parent, false)
        return AnimeCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeCategoryViewHolder, position: Int) {
        val anime = dataList[position]
        holder.bindView(anime)
    }

    override fun getItemCount(): Int {
        if(dataList.size > 0){
            return dataList.size
        }else {
            return 0
        }
    }

    inner class AnimeCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(cat: Genre){
            itemView.findViewById<TextView>(R.id.animeDetail_category_item_text).text = cat.name
        }
    }


}