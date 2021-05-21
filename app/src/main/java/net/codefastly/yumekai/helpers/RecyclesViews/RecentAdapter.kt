package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.ColorFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.Recent.RecentViewModel
import net.codefastly.yumekai.models.recents.ModelDTO
import net.codefastly.yumekai.models.recents.RecentsResponse
import net.codefastly.yumekai.models.recents.Result

class RecentAdapter(private val context: Context) :
    RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {
    private var dataList: MutableLiveData<List<ModelDTO>> = MutableLiveData<List<ModelDTO>>()
    private lateinit var itemHistoryAdapter: ItemRecentAnimeAdapter
    private lateinit var itemMovieAdapter: ItemRecentAnimeAdapter

    companion object {
        private val HISTORY_CATEGORY_ID = 1
        private val MOVIE_CATEGORY_ID = 2
    }

    fun setData(data: List<ModelDTO>) {
        dataList.value = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_recent_recyclerview, parent, false)
        return RecentViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        dataList.observeForever(Observer {
            var item = it[position]
            holder.bind(item)
            setItemsInCategoryRV(holder.itemView.findViewById(R.id.item_recents_recyclerView), item)
        })

    }

    override fun getItemCount(): Int {
        if (dataList.value != null) {
            if (dataList.value!!.size > 0) {
                return dataList.value!!.size
            } else {
                return 0
            }
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
                    findViewById<ImageView>(R.id.item_recent_img).background.setTint(
                        resources.getColor(
                            iconTint
                        )
                    )
                    with(findViewById<Button>(R.id.item_recent_button)) {
                        this.setText(textButton)
                        setOnClickListener {
                            findNavController().navigate(R.id.action_recentFragment_to_historyFragment)
                        }
                    }

                }
            }
        }
    }

    fun setItemsInCategoryRV(recyclerView: RecyclerView, items: ModelDTO) {
        itemHistoryAdapter = ItemRecentAnimeAdapter(context)
        itemMovieAdapter = ItemRecentAnimeAdapter(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.adapter = itemHistoryAdapter
        itemHistoryAdapter.setData(items.itemList.results)
        itemHistoryAdapter.notifyDataSetChanged()


    }
}
