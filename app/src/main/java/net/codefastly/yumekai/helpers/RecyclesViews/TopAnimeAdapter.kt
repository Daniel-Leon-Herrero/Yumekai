package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.models.ranking.Top

class TopAnimeAdapter(private val context: Context) : RecyclerView.Adapter<TopAnimeAdapter.TopAnimeViewHolder>() {

    private var dataList = listOf<Top>()

    fun setData(data: List<Top>){
        dataList = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopAnimeAdapter.TopAnimeViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TopAnimeAdapter.TopAnimeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class TopAnimeViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        fun bind(){

        }
    }

}