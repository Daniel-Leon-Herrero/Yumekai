package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.ranking.RankingViewModel
import net.codefastly.yumekai.models.ranking.RankingTag

class ItemTagAdapter(private val context: Context, private val rankingAdapter: TopAnimeAdapter,private val viewModel: RankingViewModel, private val owner:LifecycleOwner) :
    RecyclerView.Adapter<ItemTagAdapter.ItemTagViewHolder>() {

    private var dataList = listOf<RankingTag>()

    fun setData(data: List<RankingTag>) {
        dataList = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemTagAdapter.ItemTagViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_anime_category, parent, false)
        return ItemTagViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemTagAdapter.ItemTagViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        if (dataList.size > 0) {
            return dataList.size
        } else {
            return 0
        }
    }

    inner class ItemTagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tag: RankingTag) {
            itemView.findViewById<TextView>(R.id.animeDetail_category_item_text).text = tag.tagName
            itemView.setOnClickListener {
                viewModel.getData(tag.position).observe(owner, Observer {
                    rankingAdapter.setData(it.top)
                    rankingAdapter.notifyDataSetChanged()
                })

            }
        }
    }
}