package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.Recent.RecentViewModel
import net.codefastly.yumekai.models.recents.ModelDTO
import net.codefastly.yumekai.models.recents.Result

class RecentAdapter(private val context: Context) :
    RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {
    private var dataList = listOf<ModelDTO>()
    private val itemDataList = listOf<Result>()
    private lateinit var itemAdapter: ItemRecentAnimeAdapter

    fun setData(data: List<ModelDTO>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_recent_recyclerview, parent, false)
        return RecentViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        var item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        if (dataList.size > 0) {
            return dataList.size
        } else {
            return 0
        }
    }

    inner class RecentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ModelDTO) {
            with(itemView) {
                with(item) {
                    findViewById<TextView>(R.id.item_recent_category).text = category
                    findViewById<TextView>(R.id.item_recent_detail).text = detail
                    findViewById<ImageView>(R.id.item_recent_img).setBackgroundResource(icon)
                    findViewById<ImageView>(R.id.item_recent_img).background.setTint(resources.getColor(iconTint))
                    with(findViewById<Button>(R.id.item_recent_button)) {
                        this.setText(textButton)
                        setOnClickListener {
                            findNavController().navigate(R.id.action_recentFragment_to_historyFragment)
                        }
                    }
                    with(findViewById<RecyclerView>(R.id.item_recents_recyclerView)){
                        itemAdapter = ItemRecentAnimeAdapter(context)
                        adapter = itemAdapter
                        item.itemList.observeForever(Observer { movies ->
                            itemAdapter.setData(movies.results)
                            itemAdapter.notifyDataSetChanged()
                        })
                    }

                }
            }
        }
    }
}